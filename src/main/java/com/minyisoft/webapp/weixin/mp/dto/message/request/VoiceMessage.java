package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 语音消息
 */
@Getter
@Setter
public class VoiceMessage extends CommonMessage {
	private String mediaId;
	private String format;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setMediaId((String) properties.get("mediaId"));
		setFormat((String) properties.get("format"));
	}
}
