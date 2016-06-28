package com.lee.util.weixin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;

public class MessageUtil {
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	public static final String EVENT_TYPE_VIEW = "VIEW";//点击带链接菜单
	
	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String msg) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));

		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * 相应微信发来的请求，即回复消息（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String setRespXml(Map respMap) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");
		//Set set =respMap.entrySet();
		root.addElement("ToUserName").addCDATA((String)respMap.get("ToUserName"));
		root.addElement("FromUserName").addCDATA((String)respMap.get("FromUserName"));
		root.addElement("CreateTime").setText((String)respMap.get("CreateTime"));
		root.addElement("MsgType").addCDATA((String)respMap.get("MsgType"));
		root.addElement("Content").addCDATA((String)respMap.get("Content"));

		return document.asXML();
	}
	// /**
	// * 文本消息对象转换成xml
	// *
	// * @param textMessage
	// * 文本消息对象
	// * @return xml
	// */
	// public static String textMessageToXml(TextMessage textMessage) {
	// xstream.alias("xml", textMessage.getClass());
	// return xstream.toXML(textMessage);
	// }
	//
	// /**
	// * 音乐消息对象转换成xml
	// *
	// * @param musicMessage
	// * 音乐消息对象
	// * @return xml
	// */
	// public static String musicMessageToXml(MusicMessage musicMessage) {
	// xstream.alias("xml", musicMessage.getClass());
	// return xstream.toXML(musicMessage);
	// }
	//
	// /**
	// * 图文消息对象转换成xml
	// *
	// * @param newsMessage
	// * 图文消息对象
	// * @return xml
	// */
	// public static String newsMessageToXml(NewsMessage newsMessage) {
	// xstream.alias("xml", newsMessage.getClass());
	// xstream.alias("item", new Article().getClass());
	// return xstream.toXML(newsMessage);
	// }
	//
	// /**
	// * 扩展xstream，使其支持CDATA块
	// *
	// * @date 2013-05-19
	// */
	// private static XStream xstream = new XStream(new XppDriver() {
	// @Override
	// public HierarchicalStreamWriter createWriter(Writer out) {
	// return new PrettyPrintWriter(out) {
	// // 对所有xml节点的转换都增加CDATA标记
	// boolean cdata = true;
	//
	// @Override
	// @SuppressWarnings("rawtypes")
	// public void startNode(String name, Class clazz) {
	// super.startNode(name, clazz);
	// }
	//
	// @Override
	// protected void writeText(QuickWriter writer, String text) {
	// if (cdata) {
	// writer.write("<![CDATA[");
	// writer.write(text);
	// writer.write("]]>");
	// } else {
	// writer.write(text);
	// }
	// }
	// };
	// }
	// });
}
