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
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setPicUrl((String)properties.get("picUrl"));
		setMediaId((String)properties.get("mediaId"));
	}
}
