package com.minyisoft.webapp.weixin.common.service;

import java.text.MessageFormat;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Optional;
import com.minyisoft.webapp.core.exception.ServiceException;
import com.minyisoft.webapp.core.security.utils.DigestUtils;
import com.minyisoft.webapp.core.security.utils.EncodeUtils;
import com.minyisoft.webapp.core.utils.mapper.json.JsonMapper;
import com.minyisoft.webapp.weixin.common.model.WeixinUserInfo;

@Service
public class WeixinCommonService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${weixin.appID}")
	private String weixinAppID;
	@Value("${weixin.appsecret}")
	private String weixinAppSecret;
	@Value("${weixin.access_token_url}")
	private String accessTokenUrl;
	@Value("${weixin.oauth2.access_token_url}")
	private String oauth2AccessTokenUrl;
	@Value("${weixin.get_user_info_url}")
	private String getUserInfoUrl;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WeixinCacheService<String> weixinCacheService;

	private static final String WEIXIN_ACCESS_TOKEN_KEY = "weixin:access_token";// 微信access_token在redis的键值

	/**
	 * 获取微信access_token
	 * 
	 * @return
	 */
	public String getAccessToken() {
		String accessToken = null;
		if (StringUtils.isNotBlank(accessToken = weixinCacheService.getValue(WEIXIN_ACCESS_TOKEN_KEY))) {
			return accessToken;
		}
		Map<String, String> resultMap = _queryFromWeixinServer(MessageFormat.format(accessTokenUrl, weixinAppID,
				weixinAppSecret));
		if (resultMap.containsKey("access_token")) {
			// 缓存access_token，过期时间比微信官方时间短1分钟
			weixinCacheService.setValue(WEIXIN_ACCESS_TOKEN_KEY, resultMap.get("access_token"),
					Integer.parseInt(resultMap.get("expires_in")) - 60);
			return resultMap.get("access_token");
		} else {
			logger.error(MessageFormat.format("获取微信access_token失败，错误码：{0}，错误提示：{1}", resultMap.get("errcode"),
					resultMap.get("errmsg")));
		}
		throw new ServiceException("无法获取微信access_token");
	}

	/**
	 * 清空微信access_token缓存
	 */
	public void clearAccessTokenCache() {
		weixinCacheService.delValue(WEIXIN_ACCESS_TOKEN_KEY);
	}

	/**
	 * 在网页授权的情况下，通过code获取微信用户对应本服务号的openId
	 * 
	 * @param weixinOAuthCode
	 * @return
	 */
	public Optional<String> getOpenIdByOAuthCode(String weixinOAuthCode) {
		if (StringUtils.isBlank(weixinOAuthCode)) {
			return Optional.absent();
		}
		Map<String, String> resultMap = _queryFromWeixinServer(MessageFormat.format(oauth2AccessTokenUrl, weixinAppID,
				weixinAppSecret, weixinOAuthCode));
		if (resultMap.containsKey("openid")) {
			return Optional.of(resultMap.get("openid"));
		} else {
			logger.error(MessageFormat.format("通过网页授权code获取微信openId失败，错误码：{0}，错误提示：{1}", resultMap.get("errcode"),
					resultMap.get("errmsg")));
		}
		return Optional.absent();
	}

	/**
	 * 查询微信指定url获取结果
	 * 
	 * @param url
	 * @return
	 */
	private Map<String, String> _queryFromWeixinServer(String url) {
		ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
		return JsonMapper.NON_EMPTY_MAPPER.fromJson(result.getBody(), JsonMapper.NON_EMPTY_MAPPER.getMapper()
				.getTypeFactory().constructMapType(Map.class, String.class, String.class));
	}

	private static final String WEIXIN_TICKET_KEY_PREFIX = "weixin:ticket:";// 微信ticket缓存的键值前缀

	/**
	 * 根据微信用户openId生成系统票据，票据用于附加到超链接中，方便绑定了账号的微信用户免手工登录系统，同时票据有失效时间，
	 * 避免超链接通过微信分享时暴露分享者的个人信息
	 * 
	 * @param weixinOpenId
	 * @return
	 */
	public String genWeixinTicket(final String weixinOpenId, final int ticketExpiredSeconds) {
		Assert.hasLength(weixinOpenId, "待生成ticket的目标微信号不能为空");
		final String ticket = EncodeUtils.encodeHex(DigestUtils.generateSalt(6));
		weixinCacheService.setValue(WEIXIN_TICKET_KEY_PREFIX + ticket, weixinOpenId, ticketExpiredSeconds);
		return ticket;
	}

	public String genWeixinTicket(final String weixinOpenId) {
		return genWeixinTicket(weixinOpenId, 0);
	}

	/**
	 * 根据微信ticket获取微信用户openId，ticket获取一次后马上失效
	 * 
	 * @param weixinTicket
	 * @return
	 */
	public Optional<String> getOpenIdByTicket(String weixinTicket) {
		if (StringUtils.isNotBlank(weixinTicket)) {
			String weixinOpenId = weixinCacheService.getValue(WEIXIN_TICKET_KEY_PREFIX + weixinTicket);
			if (StringUtils.isNotBlank(weixinOpenId)) {
				weixinCacheService.delValue(WEIXIN_TICKET_KEY_PREFIX + weixinTicket);
				return Optional.of(weixinOpenId);
			}
		}
		return Optional.absent();
	}

	/**
	 * 查询指定openId用户基本信息
	 * 
	 * @param weixinOpenId
	 * @return
	 */
	public Optional<WeixinUserInfo> queryWeixinUserInfo(String weixinOpenId) {
		ResponseEntity<String> result = restTemplate.getForEntity(
				MessageFormat.format(getUserInfoUrl, getAccessToken(), weixinOpenId), String.class);
		Map<String, Object> resultMap = JsonMapper.NON_EMPTY_MAPPER.fromJson(
				result.getBody(),
				JsonMapper.NON_EMPTY_MAPPER.getMapper().getTypeFactory()
						.constructMapType(Map.class, String.class, Object.class));
		if (resultMap.containsKey("errcode")) {
			logger.error(MessageFormat.format("获取微信用户基本信息失败，错误码：{0}，错误提示：{1}", resultMap.get("errcode"),
					resultMap.get("errmsg")));
			return Optional.absent();
		} else if (((Integer) resultMap.get("subscribe")) == 0) {
			logger.error("微信用户[" + weixinOpenId + "]尚未关注公众号，无法获取用户基本信息");
			return Optional.absent();
		} else {
			WeixinUserInfo user = new WeixinUserInfo();
			try {
				BeanUtils.populate(user, resultMap);
				return Optional.of(user);
			} catch (Exception e) {
				return Optional.absent();
			}
		}
	}
}
