package com.minyisoft.webapp.weixin.mp.service;

import static org.springframework.util.Assert.isTrue;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Optional;
import com.minyisoft.webapp.weixin.mp.dto.MpDevCredential;
import com.minyisoft.webapp.weixin.mp.util.MessageMapper;
import com.minyisoft.webapp.weixin.mp.util.MpConstant;

public abstract class AbstractMpService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Getter
	private RestTemplate restTemplate;
	@Autowired(required = false)
	private CacheManager cacheManager;

	private static final String WEIXIN_ACCESS_TOKEN_KEY_FORMAT = "{0}:access_token";// 微信access_token键值
	public static final String CACHE_NAME = "weixin";// 微信缓存名
	private static final String _EXPIRED_CACHE_SEPARATOR = "@expired:";// 超时失效分隔符
	private static final String _EXPIRED_CACHE_VALUE_FORMAT = "{0}" + _EXPIRED_CACHE_SEPARATOR + "{1,number,####}";// 超时失效缓存格式
	private Cache cache;// 微信缓存

	/**
	 * 获取微信access_token
	 * 
	 * @return
	 */
	protected String getAccessToken(MpDevCredential credential) {
		isTrue(credential != null && credential.isWellformed(), "无效的微信开发者凭据");

		final String accessTokenKey = MessageFormat.format(WEIXIN_ACCESS_TOKEN_KEY_FORMAT, credential.getId());
		Optional<String> accessToken = Optional.absent();
		if ((accessToken = getCacheValue(accessTokenKey)).isPresent()) {
			return accessToken.get();
		}
		Map<String, String> resultMap = MessageMapper.fromJson(
				restTemplate.getForObject(
						MessageFormat.format(MpConstant.ACCESS_TOKEN_URL, credential.getId(),
								credential.getSecret()), String.class),
				MessageMapper.createCollectionType(Map.class, String.class, String.class));
		if (resultMap.containsKey("access_token")) {
			// 缓存access_token，过期时间比微信官方时间短1分钟
			setCacheValue(accessTokenKey, resultMap.get("access_token"),
					Integer.parseInt(resultMap.get("expires_in")) - 60);
			return resultMap.get("access_token");
		} else {
			logger.error(MessageFormat.format("获取微信access_token失败，错误码：{0}，错误提示：{1}", resultMap.get("errcode"),
					resultMap.get("errmsg")));
		}
		throw new RuntimeException("无法获取微信access_token");
	}

	@PostConstruct
	protected void initCache() {
		cache = (cacheManager == null) ? null : cacheManager.getCache(CACHE_NAME);
	}

	protected Optional<String> getCacheValue(String key) {
		if (cache != null && StringUtils.isNoneBlank(key)) {
			ValueWrapper value = cache.get(key);
			if (value != null) {
				String cacheValue = (String) value.get();
				if (StringUtils.indexOf(cacheValue, _EXPIRED_CACHE_SEPARATOR) > 0
						&& new Date().before(new Date(Long.parseLong(cacheValue.substring(cacheValue
								.indexOf(_EXPIRED_CACHE_SEPARATOR) + _EXPIRED_CACHE_SEPARATOR.length()))))) {
					return Optional.of(cacheValue.substring(0, cacheValue.indexOf(_EXPIRED_CACHE_SEPARATOR)));
				} else {
					return Optional.of(cacheValue);
				}
			}
		}
		return Optional.absent();
	}

	protected Optional<String> getAndClearCacheValue(String key) {
		Optional<String> value = getCacheValue(key);
		cache.evict(key);
		return value;
	}

	/**
	 * @param key
	 * @param value
	 * @param expiredSeconds
	 *            <=0时不过期
	 */
	protected void setCacheValue(String key, String value, int expiredSeconds) {
		if (cache != null && StringUtils.isNotBlank(value)) {
			if (expiredSeconds > 0) {
				cache.put(
						key,
						MessageFormat.format(_EXPIRED_CACHE_VALUE_FORMAT, value,
								DateUtils.addSeconds(new Date(), expiredSeconds).getTime()));
			} else {
				cache.put(key, value);
			}
		}
	}
}
