package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 图片消息
 */
@Getter
@Setter
public class ImageMessage extends CommonMessage {
	private String picUrl;
	private String mediaId;
	
	@Override
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		setPicUrl(properties.get("picUrl"));
		setMediaId(properties.get("mediaId"));
	}
}
