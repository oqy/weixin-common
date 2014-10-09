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
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setTitle((String)properties.get("title"));
		setDescription((String)properties.get("description"));
		setUrl((String)properties.get("url"));
	}
}
