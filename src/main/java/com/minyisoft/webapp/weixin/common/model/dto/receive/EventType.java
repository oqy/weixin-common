package com.minyisoft.webapp.weixin.common.model.dto.receive;

import lombok.Getter;

/**
 * @author qingyong_ou 事件推送消息类型枚举
 */
@Getter
public enum EventType {
	SUBSCRIBE("subscribe", "订阅"), UNSUBSCRIBE("unsubscribe", "取消订阅"), SCAN("scan", "扫描带参数二维码"), LOCATION("location",
			"上报地理位置"), CLICK("click", "点击菜单拉取消息"), VIEW("view", "点击菜单跳转链接"), TEMPLATESENDJOBFINISH(
			"template_send_job_finish", "模板消息发送完毕");

	private String value, description;

	private EventType(String type, String description) {
		this.value = type;
		this.description = description;
	}

	@Override
	public String toString() {
		return value;
	}
}
