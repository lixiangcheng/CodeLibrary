package com.lee.util.weixin;

import com.lee.util.Constants;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SignUtil {
    // public static String TOKEN = "woodrecyclewx";
    // public static String APPID = "wx6e4e369fd8ba908c";
    // public static String APPSECRET = "c88cac128631765d33238437fbae0d31";

    public static String PREFIX_REP_CLASS = "com.bejoysoft.weixin.message.req.Req";
    public static String SUFFIX_REP_CLASS = "Message";

    public static String WELCOME_STR = "/::D谢天谢地,您终于来了,欢迎关注!";
    public static final Map<String, String> RESP_MAP = new HashMap();

    public static final Map<String, Integer> EVENT_KEY = new HashMap();
    public static String EVENT_RESPONSE_TIME;

    static {
        RESP_MAP.put("0", "收到,谢谢!");
        RESP_MAP.put("1", "谢谢您的反馈!");
        RESP_MAP.put("2", "谢谢反馈,已收录!");

        EVENT_KEY.put("track_21", Integer.valueOf(1));
        EVENT_KEY.put("track_22", Integer.valueOf(2));
        EVENT_KEY.put("track_23", Integer.valueOf(3));
        EVENT_KEY.put("track_24", Integer.valueOf(4));
        EVENT_KEY.put("track_99", Integer.valueOf(5));

        EVENT_RESPONSE_TIME = "60";
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    public static String appendParams(String timestamp, String nonce) {
        StringBuffer result = new StringBuffer();

        String[] arr = {Constants.TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i]);
        }

        return result.toString();
    }

    public static String encryptBySHA1(String src) {
        StringBuffer result = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(src.getBytes("utf-8"));
            for (int i = 0; i < bytes.length; i++)
                result.append(Integer.toHexString(0xFF & bytes[i] | 0xFFFFFF00).substring(6));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public static ReqMessage exchangeXml(String xml, BaseMessage bsm) throws DocumentException {
        ReqMessage obj = null;
        if (bsm == null)
            return null;

        Document doc = DocumentHelper.parseText(xml);
        Iterator iter_col = doc.selectNodes("xml").iterator();
        while (iter_col.hasNext()) {
            Element element = (Element) iter_col.next();
            String msgType = element.elementTextTrim("MsgType");

            bsm.setToUserName(element.elementTextTrim("ToUserName"));
            bsm.setFromUserName(element.elementTextTrim("FromUserName"));
            bsm.setCreateTime(element.elementTextTrim("CreateTime"));
            bsm.setMsgType(msgType);

            if ("text".equalsIgnoreCase(msgType)) {
                obj = new ReqTextMessage(bsm);
                ((ReqTextMessage) obj).setMsgId(element.elementTextTrim("MsgId"));
                ((ReqTextMessage) obj).setContent(element.elementTextTrim("Content"));
            } else {
                try {
                    String classShortName = msgType;
                    if ("event".equalsIgnoreCase(classShortName)) {
                        classShortName = element.elementTextTrim("Event");
                    }
                    Class cls = Class.forName(
                            PREFIX_REP_CLASS + toUpperCaseFirstOne(classShortName.toLowerCase()) + SUFFIX_REP_CLASS);
                    obj = (ReqMessage) cls.newInstance();

                    Iterator iter_att = element.elementIterator();
                    while (iter_att.hasNext()) {
                        Element elementsub = (Element) iter_att.next();
                        try {
                            Method method = obj.getClass().getMethod("set" + toUpperCaseFirstOne(elementsub.getName()),
                                    new Class[]{String.class});
                            method.invoke(obj, new Object[]{elementsub.getTextTrim()});
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public static String getXmlString(RespMessage respm) {
        String msgType = respm.getMsgType();
        if ("text".equalsIgnoreCase(msgType)) {
            return getTextXmlString((RespTextMessage) respm);
        }
        XMLSerializer xxx = new XMLSerializer();
        xxx.setRootName("xml");

        xxx.setElementName("item");

        JSON json = JSONSerializer.toJSON(respm);
        System.out.println(json);
        String xml = xxx.write(json);

        System.out.println(xml);
        return xml;
    }

    public static String getTextXmlString(RespTextMessage respm) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");

        root.addElement("ToUserName").addCDATA(respm.getToUserName());
        root.addElement("FromUserName").addCDATA(respm.getFromUserName());
        root.addElement("CreateTime").setText(respm.getCreateTime());
        root.addElement("MsgType").addCDATA(respm.getMsgType());
        root.addElement("Content").addCDATA(respm.getContent());

        return document.asXML();
    }

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static Map parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map map = new HashMap();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static Map parseXml(String xmlStr) throws Exception {
        // 将解析结果存储在HashMap中
        Map map = new HashMap();

        Document document = DocumentHelper.parseText(xmlStr);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

        return map;
    }

    public static JSONObject parseXml2JSON(String xmlStr) throws Exception {
        Document document = DocumentHelper.parseText(xmlStr);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        String jsonStr = "";
        // 遍历所有子节点
        for (Element e : elementList) {
            List<Element> p = e.elements();
            //System.out.println(e.getName()+":"+e.getNodeType()+e.hasContent()+p.isEmpty());
            if (!p.isEmpty()) {
                jsonStr = jsonStr + "\"" + e.getName() + "\"" + ":{";
                for (Element ee : p) {
                    jsonStr = jsonStr + "\"" + ee.getName() + "\"" + ":" + "\"" + ee.getText() + "\",";
                }
                jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
                jsonStr += "},";
            } else {
                // map.put(e.getName(), e.getText());
                jsonStr = jsonStr + "\"" + e.getName() + "\"" + ":" + "\"" + e.getText() + "\",";
            }
        }
        JSONObject jo = new JSONObject();

        jsonStr = "{" + jsonStr.substring(0, jsonStr.length() - 1) + "}";
        jo = (JSONObject) JSONObject.fromObject(jsonStr);

        return jo;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    public static void main(String[] args) {
        String xmlStr = "<xml><ToUserName><![CDATA[gh_c8aa5b9b3814]]></ToUserName>"
                + "<FromUserName><![CDATA[ozydYvwkYT9T1AjsjaoGZ-kV5Z5M]]></FromUserName>"
                + "<CreateTime>1459672042</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
                + "<Event><![CDATA[scancode_waitmsg]]></Event>" + "<EventKey><![CDATA[rselfmenu_0_0]]></EventKey>"
                + "<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>"
                + "<ScanResult><![CDATA[http://dl.56steel.com/app/dl1.htm]]></ScanResult>" + "</ScanCodeInfo>"
                + "</xml>";
        try {
            System.out.println("=====" + SignUtil.parseXml2JSON(xmlStr));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}