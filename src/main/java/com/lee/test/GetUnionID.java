package com.lee.test;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Created by lixiangcheng on 16/4/11.
 */
public class GetUnionID {
    public static void main(String[] args) {
        String access_token  = "yXPG-dc59d5UWGnXDjTJ1pyfzcQbx1suU6UNvicbxKRbGHpKNYCgj0i2SWivYSCBIrLHyy3ZGf9z1rxcrMAbrx3HEEgYdqpwk-fJqjRzc0f9iyR9ujSZ2U6ix412hC0PYJBiAFAGRJ";//QRcodeTest.get();
        String openid  = "ok31Yv7bz1nQpUlqFjxezBW3dQH4";//"ok31Yv3ivxeJjPMQelHkhodDRQEE";

//        System.out.println("token:->"+access_token);


        /*HttpClient httpclient = new HttpClient();
        try {
            GetMethod getMethod = new GetMethod(
                    "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN");
            System.out.println("executing request " + getMethod.getURI());
            // 执行get请求.
//            getMethod.addRequestHeader("Content-Type", "text/html; charset=UTF-8");
            httpclient.getParams().setContentCharset("UTF-8");
            int sendStatus = httpclient.executeMethod(getMethod);
            String responseStr = getMethod.getResponseBodyAsString();
            System.out.println(sendStatus);
            System.out.println(responseStr);

            JSONObject jsonObj = JSONObject.fromObject(responseStr);
            System.out.println(jsonObj.get("nickname"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
