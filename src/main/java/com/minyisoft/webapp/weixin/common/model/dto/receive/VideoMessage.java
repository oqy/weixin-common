package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 视频消息
 */
@Getter
@Setter
public class VideoMessage extends CommonMessage {
	private String mediaId;
	private String thumbMediaId;
	
	@Override
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		setMediaId(properties.get("mediaId"));
		setThumbMediaId(properties.get("thumbMediaId"));
	}
}
