package com.minyisoft.webapp.weixin.mp.dto.message.send;

import lombok.Getter;

@Getter
public class TemplateMessageData {
	private String value;
	private String color = "#173177";

	public TemplateMessageData(String value) {
		this.value = value;
	}

	public TemplateMessageData(String value, String color) {
		this.value = value;
		this.color = color;
	}
}
