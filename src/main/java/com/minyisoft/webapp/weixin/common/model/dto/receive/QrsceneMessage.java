package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 扫描带参数二维码事件
 */
@Getter
@Setter
public class QrsceneMessage extends EventMessage {
	private String eventKey;
	private String ticket;
	
	@Override
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		setEventKey(properties.get("eventKey"));
		setTicket(properties.get("ticket"));
	}
}
