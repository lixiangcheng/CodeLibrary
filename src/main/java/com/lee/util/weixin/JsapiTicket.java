package com.lee.util.weixin;

import java.util.Date;

public class JsapiTicket extends ErrMessage {
	private String ticket;
	private String expires_in = "7200";
	private String beginDateTime;
	private boolean outTime;

	public boolean isOutTime(String beginDateTime1) {
		this.setBeginDateTime(beginDateTime1);
		this.outTime = false;
		System.out.println("====beginDateTime===="+beginDateTime);
		//System.out.println("====outTime===="+outTime);
		if ((this.beginDateTime == null) || ("".equals(this.beginDateTime.trim()))) {
			this.outTime = true;//beginDateTime为空时需要重新获取
		}else{
			long now = new Date().getTime()/1000;
			long lastTime = Long.parseLong(getBeginDateTime())/1000;
			long expires = Long.parseLong(getExpires_in());
	
			if (now - lastTime>=expires){//当前时间减去上次请求时间大于7200秒时需要重新获取ticket
				this.outTime = true;
			}
		}
		
		return outTime;
	}


	/*	public boolean isOutTime() {
		this.outTime = false;

		if ((this.beginDateTime == null) || ("".equals(this.beginDateTime.trim()))) {
			this.outTime = true;
			return this.outTime;
		}
		try {
			long c = Long.parseLong(this.expires_in);
			long bl = Long.parseLong(this.beginDateTime);
			long nl = new Date().getTime();
			long jg = nl - bl;

			if (jg > c * 1000L - 50000L) {
				System.out.println("expires_in:" + this.expires_in);
				System.out.println("beginDateTime:" + this.beginDateTime);
				System.out.println("nowDateTime:" + nl);

				this.outTime = true;
			}
		} catch (Exception e) {
			this.outTime = true;
		}

		return this.outTime;
	}*/

/*	public Long getExpires() {
		if ((this.beginDateTime == null) || ("".equals(this.beginDateTime.trim()))) {
			return Long.valueOf(0L);
		}

		long bl = Long.parseLong(this.beginDateTime);
		long nl = new Date().getTime();
		long jg = nl - bl;
		return Long.valueOf(jg);
	}*/

	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getExpires_in() {
		return this.expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getBeginDateTime() {
		return this.beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}
}