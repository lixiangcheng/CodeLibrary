package com.lee.util.controller;

import com.lee.util.Constants;
import com.lee.util.weixin.HttpJsonUtil;
import com.lee.util.weixin.JsapiTicket;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
public class GetWechatSign {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/wecaht/getWechatSign.htm")
    @ResponseBody
    public Map<String, Object> getSign(@RequestParam(value = "url") String url) {
        Map<String, Object> map = new HashedMap();
        System.out.println("====request.getServletContext().getAttribute(Constants.TICKET_BEGIN_TIME)=" + request.getServletContext().getAttribute(Constants.TICKET_BEGIN_TIME));
        boolean isOutTime = new JsapiTicket().isOutTime((String) request.getServletContext().getAttribute(Constants.TICKET_BEGIN_TIME));
        if (isOutTime) {
            try {
                System.out.println("====getTicket start====" + url);
                map = HttpJsonUtil.getTicket(request.getServletContext(), url);
                System.out.println("====map=" + map);
                System.out.println("====getTicket end====" + url);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("====直接读取ticket====" + url);
                ServletContext context = request.getServletContext();
                map.put(Constants.NONCESTR, context.getAttribute(Constants.NONCESTR));
                map.put(Constants.TIMESTAMP, context.getAttribute(Constants.TIMESTAMP));
                map.put(Constants.APPID_NAME, context.getAttribute(Constants.APPID_NAME));
                map.put(Constants.ACCESS_TOKEN, context.getAttribute(Constants.ACCESS_TOKEN));
                map.put(Constants.JSAPITICKET, context.getAttribute(Constants.JSAPITICKET));
                String string1 = "jsapi_ticket=" + context.getAttribute(Constants.JSAPITICKET) +
                        "&noncestr=" + context.getAttribute(Constants.NONCESTR) +
                        "&timestamp=" + context.getAttribute(Constants.TIMESTAMP) +
                        "&url=" + url;
                //map.put(Constants.TICKET, context.getAttribute(Constants.TICKET));
                map.put(Constants.TICKET, HttpJsonUtil.getSignatureByUrl(string1));//根据传入的URL来生成签名
                map.put(Constants.TICKET_BEGIN_TIME, (String) request.getServletContext().getAttribute(Constants.TICKET_BEGIN_TIME));
                System.out.println("====map=" + map);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return map;

    }

}
