package com.minyisoft.webapp.weixin.common.dto.receive;

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
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		setRecognition(properties.get("recognition"));
	}
}
