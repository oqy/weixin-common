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
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setMediaId((String) properties.get("mediaId"));
		setThumbMediaId((String) properties.get("thumbMediaId"));
	}
}
