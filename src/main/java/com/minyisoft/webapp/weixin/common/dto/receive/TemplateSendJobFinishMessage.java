package com.minyisoft.webapp.weixin.common.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 模板消息发送完毕事件
 */
@Getter
@Setter
public class TemplateSendJobFinishMessage extends EventMessage {
	private String status;
	
	@Override
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		setStatus(properties.get("status"));
	}
}
