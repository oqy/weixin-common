package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Date;
import java.util.Map;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 消息接收基类对象
 */
@Getter
@Setter
public abstract class Message {
	/**
	 * 开发者微信号
	 */
	private String toUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private Date createTime;
	/**
	 * MsgType
	 */
	private MessageType msgType;

	protected void fillProperty(Map<String, String> properties) {
		Assert.isTrue(properties != null && !properties.isEmpty());
		setToUserName(properties.get("toUserName"));
		setFromUserName(properties.get("fromUserName"));
		if (properties.containsKey("createTime")) {
			setCreateTime(new Date(Long.parseLong(properties.get("createTime")) * 1000L));
		}
	}
}
