package com.minyisoft.webapp.weixin.mp.dto.message.send;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

/**
 * @author qingyong_ou 模板消息
 */
@Getter
@Setter
public class TemplateMessage {
	// 目标客户微信openId
	@JsonProperty("touser")
	private String toUser;
	// 消息模板id
	@JsonProperty("template_id")
	private String templateId;
	// 超链接
	private String url;
	// 标题颜色
	@JsonProperty("topcolor")
	private String topColor = "#FF0000";
	// 数据
	private Map<String, TemplateMessageData> data = Maps.newLinkedHashMap();
}
