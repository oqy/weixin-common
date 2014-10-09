package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 自定义菜单事件
 */
@Getter
@Setter
public class MenuMessage extends EventMessage {
	private String eventKey;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setEventKey((String) properties.get("eventKey"));
	}
}
