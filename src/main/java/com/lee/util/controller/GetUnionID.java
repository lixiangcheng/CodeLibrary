package com.lee.util.controller;

import com.lee.util.Constants;
import com.lee.util.weixin.HttpJsonUtil;
import com.lee.util.weixin.JsapiTicket;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * lizhiheng
 */
@Controller
public class GetUnionID {

    public static JSONObject getNickName(String openid, HttpServletRequest request) {
        String access_token = (String) request.getServletContext().getAttribute(Constants.ACCESS_TOKEN);
        boolean isOutTime = new JsapiTicket().isOutTime((String) request.getServletContext().getAttribute(Constants.TICKET_BEGIN_TIME));
        try {
            if (isOutTime || access_token == null || "".equals(access_token)) {
                Map<String, Object> map = HttpJsonUtil.getTicket(request.getServletContext(), Constants.SAFE_DOMAIN);//没有链接时，用安全域名
            }
            access_token = (String) request.getServletContext().getAttribute(Constants.ACCESS_TOKEN);//重新取
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        HttpClient httpclient = new HttpClient();
        JSONObject jsonObj = new JSONObject();
        try {
            GetMethod getMethod = new GetMethod(
                    "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN");
            System.out.println("executing request " + getMethod.getURI());
            // 执行get请求.
            httpclient.getParams().setContentCharset("UTF-8");
            int sendStatus = httpclient.executeMethod(getMethod);
            String responseStr = getMethod.getResponseBodyAsString();
            System.out.println("===responseStr" + responseStr);
            jsonObj = JSONObject.fromObject(responseStr);
            //String nickName = "";
            //nickName = (String) jsonObj.get("nickname");
            /*
            {
                "subscribe": 1, 
                "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
                "nickname": "Band", 
                "sex": 1, 
                "language": "zh_CN", 
                "city": "广州", 
                "province": "广东", 
                "country": "中国", 
                "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0", 
               "subscribe_time": 1382694957,
               "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
               "remark": "",
               "groupid": 0
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
