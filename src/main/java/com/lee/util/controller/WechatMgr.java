package com.lee.util.controller;

import com.lee.util.Constants;
import com.lee.util.weixin.MessageUtil;
import com.lee.util.weixin.SignUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WechatMgr {

    @RequestMapping(value = {"/wechat/messageRecv.htm"}, method = RequestMethod.GET)
    public void verify(@RequestParam Map<String, Object> queryMap, HttpServletRequest request,
                       HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        // 接收参数微信加密签名、 时间戳、随机数
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        String token = Constants.TOKEN;//"Cenlink2015";// Constants.TOKEN
        System.out.println("queryMap=" + queryMap);
        System.out.println("signature=" + signature);
        System.out.println("timestamp=" + timestamp);
        System.out.println("nonce=" + nonce);
        System.out.println("echostr=" + echostr);

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        PrintWriter out = null;
        try {
            out = response.getWriter();
            boolean bl = SignUtil.checkSignature(token, signature, timestamp, nonce);
            System.out.println("验签结果:" + bl);
            if (bl) {
                out.print(echostr);
                return;
            } else {
                out.print("error");
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }
    }

    @RequestMapping(value = {"/wechat/messageRecv.htm"}, method = RequestMethod.POST)
    public void messageRecv(@RequestParam Map<String, Object> queryMap, HttpServletRequest request,
                            HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        // 接收参数微信加密签名、 时间戳、随机数
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        String token = Constants.TOKEN;//"Cenlink2015";// Constants.TOKEN
        System.out.println("queryMap=" + queryMap);
        System.out.println("signature=" + signature);
        System.out.println("timestamp=" + timestamp);
        System.out.println("nonce=" + nonce);
        System.out.println("echostr=" + echostr);
        // 通过检验signature对请求进行校验
        boolean bl = SignUtil.checkSignature(token, signature, timestamp, nonce);
        if (!bl) {
            System.out.println("验签失败:" + bl);
        }

        PrintWriter out = null;
        try {
            String msgXml = IOUtils.toString(request.getInputStream(), "UTF-8");// 收到的信息
            System.out.println("来自微信调用:" + msgXml);

            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            // xml请求解析
            JSONObject requestMap = SignUtil.parseXml2JSON(msgXml);
            String fromUserName = (String) requestMap.get("FromUserName");// 发送方帐号（open_id）
            String toUserName = (String) requestMap.get("ToUserName"); // 公众帐号
            String msgType = (String) requestMap.get("MsgType"); // 消息类型
            String Content = (String) requestMap.get("Content"); // 消息内容
            String msgId = (String) requestMap.get("MsgId"); // 消息内容

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {// 文本消息
                respContent = "您发送的是文本消息:" + Content;
                /*
                 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
				 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
				 * FromUserName> <CreateTime>1459670714</CreateTime>
				 * <MsgType><![CDATA[text]]></MsgType>
				 * <Content><![CDATA[我发消息]]></Content>
				 * <MsgId>6269237979975931030</MsgId>
				 */
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {// 图片消息
                respContent = "您发送的是图片消息" + msgType + "," + requestMap.get("MediaId") + ":" + requestMap.get("PicUrl");
                /*
				 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
				 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
				 * FromUserName> <CreateTime>1459670879</CreateTime>
				 * <MsgType><![CDATA[image]]></MsgType>
				 * <PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/
				 * nqmPia8bkr5mo89kqEW05znv2GmUuN0jxoyGxSSPicTdDNXiceUXLGvbs9cE55Iqo0oYBsGsefWDv5L71ID57doUw
				 * /0]]></PicUrl> <MsgId>6269238688645535030</MsgId>
				 * <MediaId><![CDATA[
				 * CS0bgfLbdhvyzm6lVrPAONAGVMJmALG0GY3vFdK_1FI73qtZcnD3vaUagujFgThy
				 * ]]></MediaId> </xml>
				 */
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {// 地理位置消息
                respContent = "您发送的是地理位置消息," + requestMap.get("Label") + ":" + requestMap.get("Location_X") + ","
                        + requestMap.get("Location_Y");
				/*
				 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
				 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
				 * FromUserName> <CreateTime>1459670997</CreateTime>
				 * <MsgType><![CDATA[location]]></MsgType>
				 * <Location_X>31.336315</Location_X>
				 * <Location_Y>121.490524</Location_Y> <Scale>16</Scale>
				 * <Label><![CDATA[宝山区淞南镇盛世宝邸(淞良路西)]]></Label>
				 * <MsgId>6269239195451676063</MsgId>
				 */
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {// 链接消息
                respContent = "您发送的是链接消息！";
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {// 音频消息
                respContent = "您发送的是音频消息！";
				/*
				 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
				 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
				 * FromUserName> <CreateTime>1459671871</CreateTime>
				 * <MsgType><![CDATA[voice]]></MsgType> <MediaId><![CDATA[
				 * CTo6Gw9XyHoe4Yc4g3ODTOhb_wJbJBuD5aUCfMP3wA0glFYQaP6ug25zWPVDx29q
				 * ]]></MediaId> <Format><![CDATA[amr]]></Format>
				 * <MsgId>6269242949253093609</MsgId>
				 * <Recognition><![CDATA[]]></Recognition> </xml>
				 */
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
                // 事件类型
                String eventType = (String) requestMap.get("Event");
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
                    respContent = "谢谢您的关注！";
                    try {
                        boolean result = true;/*
                        拿到openid  和 被绑定的用户id
                        providerService.scanBundling((String) requestMap.get("FromUserName"),
								((String) requestMap.get("EventKey")).replace("qrscene_", ""));*/
                        if (result) {
                            System.out.println("推荐商家绑定成功");
                        } else {
                            System.out.println("推荐商家绑定失败");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
					/*
					 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
					 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
					 * FromUserName> <CreateTime>1459670802</CreateTime>
					 * <MsgType><![CDATA[event]]></MsgType>
					 * <Event><![CDATA[subscribe]]></Event>
					 * <EventKey><![CDATA[]]></EventKey> </xml>
					 */
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
                    System.out.println(fromUserName + "，您取消了订阅");
                    return;
					/*
					 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
					 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
					 * FromUserName> <CreateTime>1459670757</CreateTime>
					 * <MsgType><![CDATA[event]]></MsgType>
					 * <Event><![CDATA[unsubscribe]]></Event>
					 * <EventKey><![CDATA[]]></EventKey> </xml>
					 */
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                    respContent = eventType + "菜单点击自定义事件！";
                    // }else
                    // if(eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){//点击菜单
                } else if (eventType.equals("VIEW")) {// 点击菜单
                    respContent = eventType + "," + requestMap.get("EventKey");
					/*
					 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
					 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
					 * FromUserName> <CreateTime>1459671208</CreateTime>
					 * <MsgType><![CDATA[event]]></MsgType>
					 * <Event><![CDATA[VIEW]]></Event>
					 * <EventKey><![CDATA[http://www.magicoo.com/wood/wxMenu/
					 * service_info.jsp]]></EventKey> <MenuId>416687012</MenuId>
					 */
                } else if (eventType.equals("scancode_waitmsg")) {// 扫描菜单带提示
                    respContent = eventType + "," + requestMap.get("EventKey") + "," + requestMap.get("ScanCodeInfo");
					/*
					 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
					 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
					 * FromUserName> <CreateTime>1459672042</CreateTime>
					 * <MsgType><![CDATA[event]]></MsgType>
					 * <Event><![CDATA[scancode_waitmsg]]></Event>
					 * <EventKey><![CDATA[rselfmenu_0_0]]></EventKey>
					 * <ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>
					 * <ScanResult><![CDATA[http://dl.56steel.com/app/dl1.htm]]>
					 * </ScanResult> </ScanCodeInfo> </xml>
					 */
                } else if (eventType.equals("scancode_push")) {// 扫描菜单直接跳转
                    JSONObject jo = (JSONObject) requestMap.get("ScanCodeInfo");
                    respContent = eventType + "," + requestMap.get("EventKey") + "," + jo.get("ScanType") + "："
                            + jo.get("ScanResult");
					/*
					 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
					 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
					 * FromUserName> <CreateTime>1459672408</CreateTime>
					 * <MsgType><![CDATA[event]]></MsgType>
					 * <Event><![CDATA[scancode_push]]></Event>
					 * <EventKey><![CDATA[rselfmenu_0_1]]></EventKey>
					 * <ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>
					 * <ScanResult><![CDATA[http://dl.56steel.com/app/dl1.htm]]>
					 * </ScanResult> </ScanCodeInfo> </xml>
					 */
                } else if (eventType.equals("SCAN")) {// 微信扫一扫二维码，关注微信并传key到后台，关联出推荐人。
                    respContent = eventType + "," + requestMap.get("EventKey") + "," + requestMap.get("Ticket");
					/*
					 * <xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>
					 * <FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></
					 * FromUserName> <CreateTime>1459675283</CreateTime>
					 * <MsgType><![CDATA[event]]></MsgType>
					 * <Event><![CDATA[SCAN]]></Event>
					 * <EventKey><![CDATA[1000]]></EventKey> <Ticket><![CDATA[
					 * gQGQ8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2ZrUnFSdWJsQU45NWRONVdabWdMAAIEjET5VgMEAAAAAA
					 * ==]]></Ticket> </xml>
					 */
                    try {
                        // 将微信扫码后的信息.根据openid跟providerId 关联起来.
                        boolean result = true;/*
                        providerService.scanBundling((String) requestMap.get("FromUserName"),
                                (String) requestMap.get("EventKey"));*/
                        if (result) {
                            System.out.println("推荐商家绑定成功");
                        } else {
                            System.out.println("推荐商家绑定失败");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {
                    respContent = requestMap.toString();
                }
            }

            // 回复文本消息
            System.out.println("Content:" + respContent);
            Map<String, String> responseMap = new HashMap<String, String>();
            responseMap.put("ToUserName", fromUserName);
            responseMap.put("FromUserName", toUserName);
            responseMap.put("CreateTime", Long.valueOf(new Date().getTime()).toString());
            responseMap.put("MsgType", MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            responseMap.put("Content", "感谢关注！木回收将与您一同创造更美好的生活环境。");
            //responseMap.put("Content", respContent);
            String respXml = MessageUtil.setRespXml(responseMap);
            System.out.println("微信回复:" + respXml);
            out = response.getWriter();
            out.println(respXml);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }
    }
}
