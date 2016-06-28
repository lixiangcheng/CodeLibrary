package com.lee.util.weixin;

import java.io.PrintStream;
import java.util.Date;

public class AccessToken {
	private String access_token;
	private String expires_in;
	private String beginDateTime;
	private boolean outTime;

	public boolean isOutTime() {
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

			if (jg > c * 1000L - 5000L) {
				System.out.println("expires_in:" + this.expires_in);
				System.out.println("beginDateTime:" + this.beginDateTime);
				System.out.println("nowDateTime:" + nl);

				this.outTime = true;
			}
		} catch (Exception e) {
			this.outTime = true;
		}

		return this.outTime;
	}

	public String getBeginDateTime() {
		return this.beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public String getAccess_token() {
		return this.access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return this.expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
}