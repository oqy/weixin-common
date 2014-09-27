package com.minyisoft.webapp.weixin.common.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.minyisoft.webapp.core.utils.mapper.json.JsonMapper;
import com.minyisoft.webapp.weixin.common.model.dto.send.Article;
import com.minyisoft.webapp.weixin.common.model.dto.send.NewsMessage;
import com.minyisoft.webapp.weixin.common.model.dto.send.TemplateMessage;
import com.minyisoft.webapp.weixin.common.model.dto.send.TemplateMessageData;
import com.minyisoft.webapp.weixin.common.model.dto.send.TextMessage;

/**
 * @author qingyong_ou 微信消息发送服务
 */
@Service
public class WeixinPostService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${weixin.send_message_url}")
	private String sendMessageUrl;
	@Value("${weixin.send_template_message_url}")
	private String sendTemplateMessageUrl;
	@Autowired
	private WeixinCommonService weixinCommonService;
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 向指定微信用户发送文本消息
	 * 
	 * @param weixinOpenId
	 * @param content
	 * @return
	 */
	public boolean postTextMessage(String weixinOpenId, String content) {
		Assert.hasLength(weixinOpenId, "待发消息目标微信号不能为空");
		Assert.hasLength(content, "待发消息不能为空");

		TextMessage m = new TextMessage();
		m.setToUser(weixinOpenId);
		m.setContent(content);
		Map<String, String> resultMap = _postToWeixinServer(
				MessageFormat.format(sendMessageUrl, weixinCommonService.getAccessToken()),
				JsonMapper.NON_EMPTY_MAPPER.toJson(m));
		if (resultMap.containsKey("errcode") && !"0".equals(resultMap.get("errcode"))) {
			logger.error("发送微信文本消息失败，目标openId:" + weixinOpenId + "，错误码：" + resultMap.get("errcode"));
			return false;
		}
		return true;
	}

	/**
	 * 发送单条图文消息
	 * 
	 * @param weixinOpenId
	 * @param article
	 * @return
	 */
	public boolean postNewsMessage(String weixinOpenId, Article article) {
		Assert.notNull(article, "待发消息不能为空");
		List<Article> articles = Lists.newArrayList();
		articles.add(article);
		return postNewsMessage(weixinOpenId, articles);
	}

	/**
	 * 发送多条图文消息
	 * 
	 * @param weixinOpenId
	 * @param articles
	 * @return
	 */
	public boolean postNewsMessage(String weixinOpenId, List<Article> articles) {
		Assert.hasLength(weixinOpenId, "待发消息目标微信号不能为空");
		Assert.isTrue(articles != null && !articles.isEmpty(), "待发消息不能为空");

		NewsMessage m = new NewsMessage();
		m.setArticles(articles);
		m.setToUser(weixinOpenId);
		Map<String, String> resultMap = _postToWeixinServer(
				MessageFormat.format(sendMessageUrl, weixinCommonService.getAccessToken()),
				JsonMapper.NON_EMPTY_MAPPER.toJson(m));
		if (resultMap.containsKey("errcode") && !"0".equals(resultMap.get("errcode"))) {
			logger.error("发送微信图文消息失败，目标openId:" + weixinOpenId + "，错误码：" + resultMap.get("errcode"));
			return false;
		}
		return true;
	}

	/**
	 * 发送模板消息
	 * 
	 * @param weixinOpenId
	 * @param templateId
	 * @param url
	 * @param data
	 * @return
	 */
	public boolean postTemplateMessage(String weixinOpenId, String templateId, String url,
			Map<String, TemplateMessageData> data) {
		Assert.hasLength(weixinOpenId, "待发消息目标微信号不能为空");
		Assert.hasLength(templateId, "待发模板消息ID不能为空");
		Assert.isTrue(data != null && !data.isEmpty(), "待发消息不能为空");

		TemplateMessage message = new TemplateMessage();
		message.setData(data);
		message.setToUser(weixinOpenId);
		message.setTemplateId(templateId);
		message.setUrl(StringUtils.isBlank(url) ? "" : url);
		Map<String, String> resultMap = _postToWeixinServer(
				MessageFormat.format(sendTemplateMessageUrl, weixinCommonService.getAccessToken()),
				JsonMapper.NON_EMPTY_MAPPER.toJson(message));
		if (resultMap.containsKey("errcode") && !"0".equals(resultMap.get("errcode"))) {
			logger.error("发送微信模板消息失败，目标openId:" + weixinOpenId + "，错误码：" + resultMap.get("errcode"));
			return false;
		}
		return true;
	}

	/**
	 * 向微信服务器指定url发送post请求
	 * 
	 * @param url
	 * @param request
	 * @return
	 */
	private Map<String, String> _postToWeixinServer(String url, Object request) {
		logger.info("发送微信消息：" + request);
		ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);
		return JsonMapper.NON_EMPTY_MAPPER.fromJson(result.getBody(), JsonMapper.NON_EMPTY_MAPPER.getMapper()
				.getTypeFactory().constructMapType(Map.class, String.class, String.class));
	}
}
