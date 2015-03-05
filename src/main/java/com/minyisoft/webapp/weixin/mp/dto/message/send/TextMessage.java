package com.minyisoft.webapp.weixin.mp.dto.message.send;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 文本消息
 */
@Getter
@Setter
public class TextMessage {
	private String content;

	public TextMessage(String content) {
		this.content = content;
	}
}
