package com.minyisoft.webapp.weixin.common.model;

import java.util.Date;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 可失效缓存对象
 * @param <T>
 */
@Getter
@Setter
public class ExpirableCacheItem<T> {
	// 缓存值
	private T value;
	// 失效时间
	private Date expireDate;

	public ExpirableCacheItem() {

	}

	public ExpirableCacheItem(T value, int expiredSeconds) {
		this.value = value;
		this.expireDate = DateTime.now().plusSeconds(expiredSeconds).toDate();
	}

	public boolean isValid() {
		return new Date().before(expireDate);
	}
}
