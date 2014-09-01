package com.minyisoft.webapp.weixin.common.dto.send;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 文本消息
 */
@Getter
@Setter
public class TextMessage extends CustomerServiceMessage {
	private String content;

	@Override
	public CustomerServiceMessageType getMessageType() {
		return CustomerServiceMessageType.TEXT;
	}

}
