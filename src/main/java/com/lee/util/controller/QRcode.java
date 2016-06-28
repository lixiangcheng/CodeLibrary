package com.lee.util.controller;

import com.lee.util.Constants;
import com.lee.util.weixin.JsapiTicket;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取带参数的二维码
 * Created by lixiangcheng on 16/4/23.
 */
public class QRcode {

    /**
     * 为服务商等生成自带  id 的二维码
     *
     * @param sceneId 服务商id
     * @param request
     * @return
     */
    public static String getQRcodeWithParam(Integer sceneId, HttpServletRequest request) {
        String token = getToken(request);
        ;
        String paramStr = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\": {\"scene\": {\"scene_id\": " + sceneId + "}}}";
        String urlStr = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
        String ticket = sendRequest4Ticket(urlStr, token, paramStr);
        JSONObject jsonObj = JSONObject.fromObject(ticket);
        return (String) jsonObj.get("ticket");
    }

    public static String sendRequest4Ticket(String urlStr, String token, String paramStr) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(urlStr + token);
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

    public static String getToken(HttpServletRequest request) {
        String access_token = "";
        boolean isOutTime = new JsapiTicket().isOutTime((String) request.getServletContext().getAttribute(Constants.TICKET_BEGIN_TIME));
        if (isOutTime) {
            try {
                HttpClient httpclient = new HttpClient();
                GetMethod getMethod = new GetMethod(
                        "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constants.APPID + "&secret="
                                + Constants.APPSECRET + "");
                System.out.println("executing request " + getMethod.getURI());
                // 执行get请求.
                int sendStatus = httpclient.executeMethod(getMethod);
                String responseStr = getMethod.getResponseBodyAsString();
                JSONObject jsonObj = JSONObject.fromObject(responseStr);
                access_token = (String) jsonObj.get("access_token");
                request.getServletContext().setAttribute(Constants.ACCESS_TOKEN, jsonObj.get("access_token"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("====直接读取ticket====");
            access_token = (String) request.getServletContext().getAttribute(Constants.ACCESS_TOKEN);
        }
        return access_token;
    }
}
