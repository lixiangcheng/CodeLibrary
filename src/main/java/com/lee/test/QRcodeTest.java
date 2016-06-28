package com.lee.test;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by lixiangcheng on 16/3/27.
 */
public class QRcodeTest {

	public static void main(String[] args) {
		 String token = get();
//
//		 String token =
//		 "5YmID_SXH1PnpCRGJOSgTSV6K-cQzl5GWjfwJfid-VCZhbCzYvtmNuEwGmFiREtKRrJWigKLt4-1AYumfRwJgrI6COYWJg6cAWAPItiX_D_ZQeE0bYUdCrLwtq5Uw5IsFTOeACAYMH";
//		 String paramStr = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\": {\"scene\": {\"scene_id\": 1000}}}";
//		 String urlStr="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
//		 String ticket=sendRequest4Ticket(urlStr,token,paramStr);
//		 System.out.println(ticket);

		String menuResult = createMenu(token);
//		System.out.println(menuResult);
//
//		String ticket = "gQGQ8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2ZrUnFSdWJsQU45NWRONVdabWdMAAIEjET5VgMEAAAAAA==";
//		System.out.println(URLEncoder.encode(ticket));
		//https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGQ8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2ZrUnFSdWJsQU45NWRONVdabWdMAAIEjET5VgMEAAAAAA%3D%3D
	}

	public static String get() {
		String access_token = "";
		//String appid = "wx6e4e369fd8ba908c";//wzc
		//String appkey = "c88cac128631765d33238437fbae0d31";

		//String appid = "wx09da28db4f53bd98";//lzh
		//String appkey = "f02e72e58e3892eaf3500203e8d9d7a6";
		
		//http://www.muhuishou.cn/
		String appid = "wx8b8a290ca11b24a4";//lzh
		String appkey = "d5f3e5c661d6eed04282059b0d40c1cc";

		HttpClient httpclient = new HttpClient();
		try {
			GetMethod getMethod = new GetMethod(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret="
							+ appkey + "");
			System.out.println("executing request " + getMethod.getURI());
			// 执行get请求.
			int sendStatus = httpclient.executeMethod(getMethod);
			String responseStr = getMethod.getResponseBodyAsString();
			System.out.println(sendStatus);
			System.out.println(responseStr);

			JSONObject jsonObj = JSONObject.fromObject(responseStr);
			System.out.println(jsonObj.get("access_token"));
			access_token = (String) jsonObj.get("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}

	/**
	 * 获取ticket
	 * 
	 * @param Data
	 * @param functionId
	 * @return
	 */
	public static String sendRequest4Ticket1(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
		String responseStr = "";
		/**
		 * {"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info":
		 * {"scene": {"scene_id": 123}}}
		 */

		HttpClient httpclient = new HttpClient();
		PostMethod postmethod = new PostMethod(url);
		NameValuePair[] postData = new NameValuePair[1];
		String params = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";
		JSONObject jsonObject = JSONObject.fromObject(params);

		postData[0] = new NameValuePair("", jsonObject.toString());
		postmethod.addParameters(postData);
		try {
			postmethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			int sendStatus = httpclient.executeMethod(postmethod);
			responseStr = postmethod.getResponseBodyAsString();
			System.out.println(sendStatus);
			System.out.println(responseStr);
		} catch (Exception e) {

		}
		return responseStr;
	}

	/**
	 * 获取ticket
	 * 
	 * @param Data
	 * @param functionId
	 * @return
	 */
	public static String sendRequest4Ticket(String urlStr, String token, String paramStr) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(urlStr+token);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.println(paramStr);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		return result;
	}

	public static String createMenu(String token) {
		String domain="http://muhuishou.anxinfloors.com/wood";//"http://www.magicoo.com/wood";
		String urlStr="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
		System.out.println(sendRequest4Ticket(urlStr, token, ""));
		String menuStr = "{" + " \"button\":[" + "{	" + "     \"type\":\"view\"," + "     \"name\":\"宝贝交易\","
				+ "     \"url\":\""+domain+"/wxMenu/user_info.jsp\"" + "  }," + "{	" + "     \"type\":\"view\"," + "     \"name\":\"我的订单\","
				+ "     \"url\":\""+domain+"/wxMenu/my_order.jsp\"" + "  },"+ "{	" + "     \"type\":\"view\"," + "     \"name\":\"商家入口\","
				+ "     \"url\":\""+domain+"/wxMenu/service_info.jsp\"" + "  }]" + " }";
//		String menuStr = "{" + " \"button\":[" + "{	" + "     \"type\":\"view\"," + "     \"name\":\"您请吩咐\","
//				+ "     \"url\":\"http://www.sohu.com/\"" + "  }," + "{	" + "     \"type\":\"view\"," + "     \"name\":\"我的订单\","
//				+ "     \"url\":\"http://www.ccb.com/\"" + "  },"+ "{	" + "     \"type\":\"view\"," + "     \"name\":\"商家入口\","
//				+ "     \"url\":\"https://git.oschina.net/\"" + "  },"+ "{	" + "     \"type\":\"view\"," + "     \"name\":\"平台简介\","
//				+ "     \"url\":\"http://www.baidu.com/\"" + "  },"+ "  {" + "     \"name\":\"菜单\","
//				+ "      \"sub_button\":[" + "      {	" + "          \"type\":\"view\"," + "         \"name\":\"搜索\","
//				+ "       \"url\":\"http://www.soso.com/\"" + "      }," + "      {" + "         \"type\":\"view\","
//				+ "         \"name\":\"视频\"," + "         \"url\":\"http://v.qq.com/\"" + "       }," + "       {"
//				+ "          \"type\":\"click\"," + "          \"name\":\"赞一下我们\","
//				+ "           \"key\":\"V1001_GOOD\"" + "       }]" + "  }]" + " }";
		urlStr = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
		//您请吩咐：WEB-INF/jsp/weChat/user_index.jsp
		//我的订单：WEB-INF/jsp/weChat/my_order.jsp
		//商家入口：WEB-INF/jsp/weChat/service_info.jsp
		//平台简介：暂无
		
		return sendRequest4Ticket(urlStr, token, menuStr);
	}
}
