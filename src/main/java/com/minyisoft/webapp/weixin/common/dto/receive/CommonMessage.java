package com.minyisoft.webapp.weixin.common.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 普通消息接收基类对象
 */
@Getter
@Setter
public abstract class CommonMessage extends Message {
	/**
	 * 消息id，64位整型
	 */
	private long msgId;

	@Override
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		if (properties.containsKey("msgId")) {
			setMsgId(Long.parseLong(properties.get("msgId")));
		}
	}
}
