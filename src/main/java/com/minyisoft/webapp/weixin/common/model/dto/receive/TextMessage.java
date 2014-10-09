package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 文本消息
 */
@Getter
@Setter
public class TextMessage extends CommonMessage {
	private String content;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setContent((String) properties.get("content"));
	}
}
