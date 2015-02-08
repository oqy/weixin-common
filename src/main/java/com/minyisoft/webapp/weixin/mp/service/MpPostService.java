package com.minyisoft.webapp.weixin.mp.service;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.isTrue;

import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.base.Optional;
import com.minyisoft.webapp.weixin.mp.dto.MpDevCredential;
import com.minyisoft.webapp.weixin.mp.dto.MpEnvelope;
import com.minyisoft.webapp.weixin.mp.dto.MpResponse;
import com.minyisoft.webapp.weixin.mp.dto.message.send.Article;
import com.minyisoft.webapp.weixin.mp.dto.message.send.CSMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.send.NewsMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.send.TemplateMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.send.TemplateMessageData;
import com.minyisoft.webapp.weixin.mp.dto.message.send.TextMessage;
import com.minyisoft.webapp.weixin.mp.util.MessageMapper;
import com.minyisoft.webapp.weixin.mp.util.MpConstant;

/**
 * @author qingyong_ou 微信消息发送服务
 */
@Service
public class MpPostService extends AbstractMpService {
	/**
	 * 向指定微信用户发送文本消息
	 * 
	 * @param envelope
	 * @param content
	 * @return
	 */
	public MpResponse postTextMessage(MpEnvelope envelope, String content) {
		isTrue(envelope != null && envelope.isCredentialWellformed() && envelope.isTargetDefined(), "待发消息目标微信号不能为空");
		hasLength(content, "待发消息不能为空");

		MpResponse response = _postToWeixinServer(
				MessageFormat.format(MpConstant.SEND_MESSAGE_URL, getAccessToken(envelope.getCredential())),
				MessageMapper.toJson(new CSMessage(envelope.getWeixinOpenId(), new TextMessage(content))));
		if (!response.isSuccessful()) {
			logger.error("发送微信文本消息失败，目标openId:" + envelope.getWeixinOpenId() + "，错误码：" + response.getErrCode());
		}
		return response;
	}

	/**
	 * 发送图文消息
	 * 
	 * @param envelope
	 * @param articles
	 * @return
	 */
	public MpResponse postNewsMessage(MpEnvelope envelope, List<Article> articles) {
		isTrue(envelope != null && envelope.isCredentialWellformed() && envelope.isTargetDefined(), "待发消息目标微信号不能为空");
		isTrue(articles != null && !articles.isEmpty(), "待发消息不能为空");

		MpResponse response = _postToWeixinServer(
				MessageFormat.format(MpConstant.SEND_MESSAGE_URL, getAccessToken(envelope.getCredential())),
				MessageMapper.toJson(new CSMessage(envelope.getWeixinOpenId(), new NewsMessage(articles))));
		if (!response.isSuccessful()) {
			logger.error("发送微信图文消息失败，目标openId:" + envelope.getWeixinOpenId() + "，错误码：" + response.getErrCode());
		}
		return response;
	}

	/**
	 * 发送模板消息
	 * 
	 * @param envelope
	 * @param templateId
	 * @param data
	 * @return
	 */
	public MpResponse postTemplateMessage(MpEnvelope envelope, String url, String templateId,
			Map<String, TemplateMessageData> data) {
		isTrue(envelope != null && envelope.isCredentialWellformed() && envelope.isTargetDefined(), "待发消息目标微信号不能为空");
		Assert.hasLength(templateId, "待发模板消息ID不能为空");
		Assert.isTrue(data != null && !data.isEmpty(), "待发消息不能为空");

		TemplateMessage message = new TemplateMessage();
		message.setData(data);
		message.setUrl(url);
		message.setToUser(envelope.getWeixinOpenId());
		message.setTemplateId(templateId);
		MpResponse response = _postToWeixinServer(MessageFormat.format(MpConstant.SEND_TEMPLATE_MESSAGE_URL,
				getAccessToken(envelope.getCredential())), MessageMapper.toJson(message));
		if (!response.isSuccessful()) {
			logger.error("发送微信模板消息失败，目标openId:" + envelope.getWeixinOpenId() + "，错误码：" + response.getErrCode());
		}
		return response;
	}

	/**
	 * 向微信服务器指定url发送post请求
	 * 
	 * @param url
	 * @param request
	 * @return
	 */
	private MpResponse _postToWeixinServer(String url, Object request) {
		logger.info("发送微信消息：" + request);
		return getRestTemplate().postForObject(url, request, MpResponse.class);
	}

	private static final String WEIXIN_TICKET_KEY_PREFIX = "ticket:";// 微信ticket键值前缀
	private static final SecureRandom random = new SecureRandom();

	/**
	 * 根据微信用户openId生成系统票据，票据用于附加到超链接中，方便绑定了账号的微信用户免手工登录系统，同时票据有失效时间，
	 * 避免超链接通过微信分享时暴露分享者的个人信息
	 * 
	 * @param weixinOpenId
	 * @return
	 */
	public String genWeixinTicket(final MpEnvelope envelope, final int ticketExpiredSeconds) {
		isTrue(envelope != null && envelope.isCredentialWellformed() && envelope.isTargetDefined(), "待发消息目标微信号不能为空");

		final byte[] bytes = new byte[6];
		random.nextBytes(bytes);
		final String ticket = Hex.encodeHexString(bytes);
		setCacheValue(WEIXIN_TICKET_KEY_PREFIX + ticket, envelope.getWeixinOpenId(), ticketExpiredSeconds);
		return ticket;
	}

	public String genWeixinTicket(MpEnvelope envelope) {
		return genWeixinTicket(envelope, 0);
	}

	/**
	 * 根据微信ticket获取微信用户openId，ticket获取一次后马上失效
	 * 
	 * @param weixinTicket
	 * @return
	 */
	public Optional<String> getOpenIdByTicket(String weixinTicket) {
		return getAndClearCacheValue(WEIXIN_TICKET_KEY_PREFIX + weixinTicket);
	}

	/**
	 * 在网页授权的情况下，通过code获取微信用户对应本服务号的openId
	 * 
	 * @param weixinOAuthCode
	 * @return
	 */
	public Optional<String> getOpenIdByOAuthCode(MpDevCredential credential, String weixinOAuthCode) {
		isTrue(credential != null && credential.isWellformed(), "无效的微信开发者凭据");

		if (StringUtils.isBlank(weixinOAuthCode)) {
			return Optional.absent();
		}
		Map<String, String> resultMap = MessageMapper.fromJson(
				getRestTemplate().getForObject(
						MessageFormat.format(MpConstant.OAUTH2_ACCESS_TOKEN_URL, credential.getId(),
								credential.getSecret(), weixinOAuthCode), String.class),
				MessageMapper.createCollectionType(Map.class, String.class, String.class));
		if (resultMap.containsKey("openid")) {
			return Optional.of(resultMap.get("openid"));
		} else {
			logger.error(MessageFormat.format("通过网页授权code获取微信openId失败，错误码：{0}，错误提示：{1}", resultMap.get("errcode"),
					resultMap.get("errmsg")));
		}
		return Optional.absent();
	}
}
