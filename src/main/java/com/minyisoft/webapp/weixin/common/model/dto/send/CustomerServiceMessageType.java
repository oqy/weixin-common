package com.minyisoft.webapp.weixin.common.model.dto.send;

import org.apache.commons.lang3.StringUtils;

/**
 * @author qingyong_ou 微信发送客服消息类型枚举
 */
public enum CustomerServiceMessageType {
	TEXT("text"), // 文本消息
	IMAGE("image"), // 图片消息
	VOICE("voice"), // 语音消息
	VIDEO("video"), // 视频消息
	MUSIC("music"), // 音乐消息
	NEWS("news"); // 图文消息

	private String type;

	private CustomerServiceMessageType(String type) {
		this.type = type;
	}
	
	public String getValue(){
		return type;
	}

	@Override
	public String toString() {
		return type;
	}
	
	public static CustomerServiceMessageType getType(String type) {
		if (StringUtils.isNotBlank(type)) {
			for (CustomerServiceMessageType mType : CustomerServiceMessageType.values()) {
				if (mType.getValue().equalsIgnoreCase(type)) {
					return mType;
				}
			}
		}
		return null;
	}
}
