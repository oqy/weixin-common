package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 链接消息
 */
@Getter
@Setter
public class LinkMessage extends CommonMessage {
	private String title;
	private String description;
	private String url;
	
	@Override
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		setTitle(properties.get("title"));
		setDescription(properties.get("description"));
		setUrl(properties.get("url"));
	}
}
