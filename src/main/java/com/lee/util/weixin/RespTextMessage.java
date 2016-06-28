package com.lee.util.weixin;

import net.sf.json.JSONString;

public class RespTextMessage extends RespMessage implements JSONString {
	private String Content;

	public String getContent() {
		return this.Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

	public String toJSONString() {
		return null;
	}
}