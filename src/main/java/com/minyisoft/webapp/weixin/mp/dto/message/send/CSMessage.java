package com.minyisoft.webapp.weixin.mp.dto.message.send;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author qingyong_ou 客服消息
 */
@Getter
public class CSMessage {
	// 接收会员
	@JsonProperty("touser")
	private String toUser;
	// 消息类型
	@JsonProperty("msgtype")
	private CSMessageType messageType;
	// 文本消息
	private TextMessage text;
	// 图文消息
	private NewsMessage news;

	public CSMessage(String toUser, TextMessage text) {
		this.toUser = toUser;
		this.text = text;
		this.messageType = CSMessageType.TEXT;
	}

	public CSMessage(String toUser, NewsMessage news) {
		this.toUser = toUser;
		this.news = news;
		this.messageType = CSMessageType.NEWS;
	}
}
