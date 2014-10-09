package com.minyisoft.webapp.weixin.common.model.dto.receive;

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
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setStatus((String) properties.get("status"));
	}
}
