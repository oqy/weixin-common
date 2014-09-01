package com.minyisoft.webapp.weixin.common.dto.receive;

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
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		setMediaId(properties.get("mediaId"));
		setFormat(properties.get("format"));
	}
}
