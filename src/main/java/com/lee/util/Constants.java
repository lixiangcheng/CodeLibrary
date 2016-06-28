package com.lee.util;

public class Constants {
	public static String LOGIN_NAME = "login_name";
	public static String USER_NAME = "user_name";
	public static String CUSTOMER_NAME = "customer_Name";

	public static String USER_INFOR = "user_infor";
	public static String CUSTOMER_INFOR = "customer_infor";

	/**
	 * 微信公众号相关参数
	 */
	public static String NONCESTR = "wechat_noncestr";
	public static String TIMESTAMP = "timestamp";
	public static String TICKET = "ticket";
	public static String TICKET_BEGIN_TIME = "ticket_begin_time";
	public static String APPID_NAME = "app_id";
	public static String ACCESS_TOKEN = "access_token";
	public static String JSAPITICKET = "jsapiticket";
	public static String showqrcodeUrl ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";//读取二维码的地址.
	
//	public static String APPID = "wx09da28db4f53bd98";
//	public static String APPSECRET = "f02e72e58e3892eaf3500203e8d9d7a6";
//	public static String TOKEN = "woodrecyclewx";
//	public static String SAFE_DOMAIN = "http://www.magicoo.com/";//安全域名
	public static String APPID =new PropertyUtil("properties.properties").getValue("WECHAT.APPID");
	public static String APPSECRET =new PropertyUtil("properties.properties").getValue("WECHAT.APPSECRET");
	public static String TOKEN =new PropertyUtil("properties.properties").getValue("WECHAT.TOKEN");
	public static String SAFE_DOMAIN =new PropertyUtil("properties.properties").getValue("WECHAT.SAFE_DOMAIN");//安全域名
}
