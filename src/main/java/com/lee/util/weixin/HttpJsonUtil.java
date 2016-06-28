package com.lee.util.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lee.util.Constants;
import com.lee.util.WebApplicationContextTool;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpJsonUtil {
	public static String REQ_CS_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

	private static String TOKEN_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	private static String TOKEN_BUNDING = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public static ErrMessage httpRequest(HttpServletRequest request, CsMessage respm) {
		return httpRequest(getAccessToken(request), REQUEST_METHOD.POST, respm);
	}

	public static ErrMessage httpRequest(WebApplicationContextTool webApplicationContextTool, CsMessage respm) {
		ErrMessage mg = httpRequest(getAccessToken(webApplicationContextTool), REQUEST_METHOD.POST, respm);

		if ((mg != null) && ("4001".equals(mg.getErrcode()))) {
			mg = httpRequest(getNewAccessToken(webApplicationContextTool), REQUEST_METHOD.POST, respm);
		}
		return mg;
	}

	public static ErrMessage httpRequest(HttpServletRequest request, REQUEST_METHOD requestMethod, CsMessage respm) {
		return httpRequest(getAccessToken(request), requestMethod, respm);
	}

	public static ErrMessage httpRequest(String accessToken, REQUEST_METHOD requestMethod, CsMessage respm) {
		JSONObject json = JSONObject.fromObject(respm);
		System.out.println(json.toString());
		JSONObject jsonObject = httpJsonRequest(REQ_CS_URL_PREFIX + "?access_token=" + accessToken,
				requestMethod.toString(), json.toString());
		return (ErrMessage) JSONObject.toBean(jsonObject, ErrMessage.class);
	}

	private static JSONObject httpJsonRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();

		OutputStream outputStream = null;
		InputStream inputStream = null;
		HttpsURLConnection httpUrlConn = null;
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new SecureRandom());

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);

			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			if (outputStr != null) {
				outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
				outputStream = null;
			}

			inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			httpUrlConn = null;

			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outputStream = null;
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				inputStream = null;
			}
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
				httpUrlConn = null;
			}
		}
		return jsonObject;
	}

	public static String getAccessToken(WebApplicationContextTool webApplicationContextTool) {
		WebApplicationContext wac = (WebApplicationContext) webApplicationContextTool.getCtx();
		ServletContext application = wac.getServletContext();
		return getAccessToken(application);
	}

	public static String getAccessToken(HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		return getAccessToken(application);
	}

	private static String getAccessToken(ServletContext application) {
		AccessToken accessTokenBean = (AccessToken) application.getAttribute("ACCESSTOKEN");

		if ((accessTokenBean == null) || (accessTokenBean.isOutTime())) {
			accessTokenBean = getNewAccessTokenBean();
			application.setAttribute("ACCESSTOKEN", accessTokenBean);
		}

		System.out.println("ACCESS_TOKEN:::" + accessTokenBean.getAccess_token());
		return accessTokenBean.getAccess_token();
	}

	private static String getNewAccessToken(WebApplicationContextTool webApplicationContextTool) {
		AccessToken accessTokenBean = getNewAccessTokenBean();
		WebApplicationContext wac = (WebApplicationContext) webApplicationContextTool.getCtx();
		ServletContext application = wac.getServletContext();
		application.setAttribute("ACCESSTOKEN", accessTokenBean);
		return accessTokenBean.getAccess_token();
	}

	public static AccessToken getNewAccessTokenBean() {
		String requestUrl = TOKEN_URL_PREFIX + "&appid=" + Constants.APPID + "&secret=" + Constants.APPSECRET;

		JSONObject jsonObj = httpJsonRequest(requestUrl, "GET", null);
		AccessToken accessTokenBean = (AccessToken) JSONObject.toBean(jsonObj, AccessToken.class);
		accessTokenBean.setBeginDateTime(Long.valueOf(new Date().getTime()).toString());

		System.out.println("NEW ACCESS_TOKEN:::" + accessTokenBean.getAccess_token());

		return accessTokenBean;
	}

	public static JSONObject httpBundingAccess(String code) {
		String wxUrl = TOKEN_BUNDING;
		wxUrl = wxUrl + "?appid=" + Constants.APPID;
		wxUrl = wxUrl + "&secret=" + Constants.APPSECRET;
		wxUrl = wxUrl + "&code=" + code;
		wxUrl = wxUrl + "&grant_type=authorization_code";
		return httpJsonRequest(wxUrl, "GET", null);
	}

	public static enum REQUEST_METHOD {
		POST, GET;
	}
    public static Map<String ,Object> getTicket(ServletContext servletContext,String url) throws IOException, NoSuchAlgorithmException {

        Map<String ,Object> result =  new HashedMap();
        ObjectMapper objectMapper = new ObjectMapper();

        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constants.APPID + "&secret=" + Constants.APPSECRET;
        String accessTokenStr = getHttpResponse(accessTokenUrl);
        AccessToken accessToken = objectMapper.readValue(accessTokenStr, AccessToken.class);

        String jsApiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken.getAccess_token() + "&type=jsapi";
        String jsApiTicketStr = getHttpResponse(jsApiTicketUrl);
        JsapiTicket jsApiTicket = objectMapper.readValue(jsApiTicketStr, JsapiTicket.class);

        String nonceStr = create_nonce_str();
        //String timestamp = create_timestamp();
        Long currentTimeMillis=System.currentTimeMillis();
        String timestamp=Long.toString(currentTimeMillis/1000);
        /*url = url.split("#")[0];
        url = url.replace("/wechat/signature", "/bonus/welfare");*/

        String string1 = "jsapi_ticket=" + jsApiTicket.getTicket() +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes("UTF-8"));
        String signature = byteToHex(crypt.digest());


        servletContext.setAttribute(Constants.NONCESTR, nonceStr);
        servletContext.setAttribute(Constants.TIMESTAMP, timestamp);
        servletContext.setAttribute(Constants.TICKET, signature);
        servletContext.setAttribute(Constants.APPID_NAME, Constants.APPID);
        servletContext.setAttribute(Constants.ACCESS_TOKEN, accessToken.getAccess_token());
        servletContext.setAttribute(Constants.JSAPITICKET, jsApiTicket.getTicket());
        servletContext.setAttribute(Constants.TICKET_BEGIN_TIME, String.valueOf(currentTimeMillis));

        result.put(Constants.NONCESTR, nonceStr);
        result.put(Constants.TIMESTAMP, timestamp);
        result.put(Constants.TICKET, signature);
        result.put(Constants.APPID_NAME, Constants.APPID);
        result.put(Constants.ACCESS_TOKEN, accessToken.getAccess_token());
        result.put(Constants.JSAPITICKET, jsApiTicket.getTicket());
        result.put(Constants.TICKET_BEGIN_TIME, String.valueOf(currentTimeMillis));

        return result;
    }

    public static String getHttpResponse(String url) throws IOException {
        HttpClient httpClient = new HttpClient();
        HttpMethod get = new GetMethod(url);
        httpClient.executeMethod(get);

        /*HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while((line = rd.readLine()) != null) {
            buffer.append(line);
        }
        String result = buffer.toString();*/
        return get.getResponseBodyAsString();
    }


    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static String getSignatureByUrl(String Url) throws IOException, NoSuchAlgorithmException{
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(Url.getBytes("UTF-8"));
        String signature = byteToHex(crypt.digest());
        return signature;
    }
}