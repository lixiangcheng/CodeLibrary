package com.lee.util.weixin;

public class ReqTextMessage extends ReqGeneralMessage {
	private String content;

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ReqTextMessage() {
	}

	public ReqTextMessage(BaseMessage gm) {
		setToUserName(gm.getToUserName());
		setFromUserName(gm.getFromUserName());
		setCreateTime(gm.getCreateTime());
		setMsgType(gm.getMsgType());
		this.content = null;
	}
}