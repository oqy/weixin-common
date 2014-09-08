package com.minyisoft.webapp.weixin.common.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.minyisoft.webapp.weixin.common.model.dto.receive.EventType;
import com.minyisoft.webapp.weixin.common.model.dto.receive.MessageType;

/**
 * @author qingyong_ou 微信用户操作跟踪对象
 */
@Getter
@Setter
public class WeixinUserTraceInfo {
	// 微信用户openId
	private String weixinOpenId;
	// 操作时间
	private Date traceTime;
	// 消息类型
	private MessageType messageType;
	// 事件类型
	private EventType eventType;
	// 备注
	private String description;
	// 消息原文
	private String messageString;
}
