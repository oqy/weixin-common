package com.minyisoft.webapp.weixin.common.model.dto.receive;

import lombok.Getter;

/**
 * @author qingyong_ou 事件推送消息类型枚举
 */
@Getter
public enum EventType {
	SUBSCRIBE("subscribe", "订阅"), // 订阅
	UNSUBSCRIBE("unsubscribe", "取消订阅"), // 取消订阅
	SCAN("scan", "扫描带参数二维码"), // 扫描带参数二维码
	LOCATION("location", "上报地理位置"), // 上报地理位置
	CLICK("click", "点击菜单拉取消息"), // 点击菜单拉取消息
	VIEW("view", "点击菜单跳转链接"), // 点击菜单跳转链接
	TEMPLATESENDJOBFINISH("template_send_job_finish", "模板消息发送完毕"), // 模板消息发送完毕
	SCANCODE_PUSH("scancode_push", "扫码推事件"), // 扫码推事件
	SCANCODE_WAITMSG("scancode_waitmsg", "扫码推事件且弹出“消息接收中”提示框"), // 扫码推事件且弹出“消息接收中”提示框
	PIC_SYSPHOTO("pic_sysphoto", "弹出系统拍照发图"), // 弹出系统拍照发图
	PIC_PHOTO_OR_ALBUM("pic_photo_or_album", "弹出拍照或者相册发图"), // 弹出拍照或者相册发图
	PIC_WEIXIN("pic_weixin", "弹出微信相册发图器"), // 弹出微信相册发图器
	LOCATION_SELECT("location_select", "弹出地理位置选择器"); // 弹出地理位置选择器

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
