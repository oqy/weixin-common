package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 普通消息接收基类对象
 */
@Getter
@Setter
public abstract class CommonMessage extends RequestMessage {
	/**
	 * 消息id，64位整型
	 */
	private long msgId;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		if (properties.containsKey("msgId")) {
			setMsgId(Long.parseLong((String) properties.get("msgId")));
		}
	}
}
