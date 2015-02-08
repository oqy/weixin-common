package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.util.Map;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 普通消息接收基类对象
 */
@Getter
@Setter
public abstract class RequestMessage extends Message {

	protected void fillProperty(Map<String, Object> properties) {
		Assert.isTrue(properties != null && !properties.isEmpty());
		setToUserName((String) properties.get("toUserName"));
		setFromUserName((String) properties.get("fromUserName"));
	}
}
