package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 语音识别消息
 */
@Getter
@Setter
public class VoiceRecognitionMessage extends VoiceMessage {
	private String recognition;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setRecognition((String) properties.get("recognition"));
	}
}
