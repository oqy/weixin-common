package com.minyisoft.webapp.weixin.common.model.dto.send;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author qinyyong_ou 客服发送消息基类
 */
@Setter
@Getter
@JsonSerialize(using = CustomerServiceMessageSerilizer.class)
public abstract class CustomerServiceMessage {
	private String toUser;
	public abstract CustomerServiceMessageType getMessageType();
}
