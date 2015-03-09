package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 关注事件
 */
@Getter
@Setter
public class SubscribeMessage extends EventMessage {
	private String eventKey;
	private String ticket;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setEventKey((String) properties.get("eventKey"));
		setTicket((String) properties.get("ticket"));
	}
}
