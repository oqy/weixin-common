package com.minyisoft.webapp.weixin.common.dto.receive;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 事件推送消息接收对象
 */
@Getter
@Setter
public class EventMessage extends Message {
	/**
	 * 事件类型
	 */
	private EventType event;
}
