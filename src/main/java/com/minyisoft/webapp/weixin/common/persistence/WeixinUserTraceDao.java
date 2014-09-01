package com.minyisoft.webapp.weixin.common.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.minyisoft.webapp.weixin.common.dto.WeixinUserTraceInfo;

/**
 * @author qingyong_ou 微信用户操作跟踪dao
 */
@Component
public interface WeixinUserTraceDao {
	/**
	 * 记录操作
	 * 
	 * @param trace
	 * @return
	 */
	int logTrace(WeixinUserTraceInfo trace);

	/**
	 * 获取可进行交互（48小时内与微信公众号进行过互动）的微信用户openId集合
	 * 
	 * @return
	 */
	List<String> getTouchableWeixinOpenIds();
}
