package com.minyisoft.webapp.weixin.mp.dto.message.send;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author qingyong_ou 微信发送客服消息类型枚举
 */
public enum CSMessageType {
	TEXT, // 文本消息
	IMAGE, // 图片消息
	VOICE, // 语音消息
	VIDEO, // 视频消息
	MUSIC, // 音乐消息
	NEWS; // 图文消息

	@JsonValue
	public String getJsonValue() {
		return name().toLowerCase();
	}
}
