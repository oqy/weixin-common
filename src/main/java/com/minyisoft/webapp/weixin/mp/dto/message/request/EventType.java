package com.minyisoft.webapp.weixin.mp.dto.message.request;

import lombok.Getter;

/**
 * @author qingyong_ou 事件推送消息类型枚举
 */
@Getter
public enum EventType {
	SUBSCRIBE("订阅"), UNSUBSCRIBE("取消订阅"), SCAN("扫描带参数二维码"), LOCATION("上报地理位置"), CLICK("点击菜单拉取消息"), VIEW("点击菜单跳转链接"), TEMPLATESENDJOBFINISH(
			"模板消息发送完毕"), SCANCODE_PUSH("扫码推事件"), SCANCODE_WAITMSG("扫码推事件且弹出“消息接收中”提示框"), PIC_SYSPHOTO("弹出系统拍照发图"), PIC_PHOTO_OR_ALBUM(
			"弹出拍照或者相册发图"), PIC_WEIXIN("弹出微信相册发图器"), LOCATION_SELECT("弹出地理位置选择器");

	private String description;

	private EventType(String description) {
		this.description = description;
	}
}
