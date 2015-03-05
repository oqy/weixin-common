package com.minyisoft.webapp.weixin.mp.dto.message.request;

import lombok.Getter;

/**
 * @author qingyong_ou 微信接收消息类型枚举
 */
@Getter
public enum MessageType {
	TEXT("text", "文本消息"), IMAGE("image", "图片消息"), VOICE("voice", "语音消息"), VIDEO("video", "视频消息"), LOCATION("location",
			"地理位置消息"), LINK("link", "链接消息"), EVENT("event", "事件消息"),

	TRANSFER_CUSTOMER_SERVICE("transfer_customer_service", "多客服");

	private String value, description;

	private MessageType(String type, String description) {
		this.value = type;
		this.description = description;
	}

	@Override
	public String toString() {
		return value;
	}
}
