package com.minyisoft.webapp.weixin.common.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.minyisoft.webapp.core.utils.redis.JedisTemplate;
import com.minyisoft.webapp.weixin.common.model.ExpirableCacheItem;

/**
 * @author qingyong_ou 缓存服务
 * @param <T>
 */
@Service
public class WeixinCacheService<T> implements InitializingBean {
	@Autowired(required = false)
	private JedisTemplate jedisTemplate;
	private Cache<String, ExpirableCacheItem<T>> cache = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (jedisTemplate == null) {
			cache = CacheBuilder.newBuilder().build();
		}
	}

	@SuppressWarnings("unchecked")
	public T getValue(String key) {
		if (jedisTemplate != null) {
			return (T) jedisTemplate.get(key);
		}
		ExpirableCacheItem<T> cacheItem = cache.getIfPresent(key);
		if (cacheItem == null) {
			return null;
		} else if (!cacheItem.isValid()) {
			cache.invalidate(key);
			return null;
		}
		return cacheItem.getValue();
	}

	public void setValue(String key, T value) {
		setValue(key, value, 0);
	}

	public void setValue(String key, T value, int expiredSeconds) {
		if (jedisTemplate != null) {
			if (expiredSeconds > 0) {
				jedisTemplate.setex(key, (String) value, expiredSeconds);
			} else {
				jedisTemplate.set(key, (String) value);
			}
		} else {
			cache.put(key, new ExpirableCacheItem<T>(value, expiredSeconds));
		}
	}

	public void delValue(String key) {
		if (jedisTemplate != null) {
			jedisTemplate.del(key);
		} else {
			cache.invalidate(key);
		}
	}
}
