package com.minyisoft.webapp.weixin.common.persistence;

import java.util.List;

import com.minyisoft.webapp.weixin.common.model.WeixinUserTraceInfo;

/**
 * @author qingyong_ou 微信用户操作跟踪dao
 */
public interface WeixinUserTraceDao {
	/**
	 * 记录操作
	 * 
	 * @param trace
	 * @return
	 */
	int logTrace(WeixinUserTraceInfo trace);

	/**
	 * 获取可进行交互（48小时内与微信公众号进行过互动）的微信用户openId
	 * 
	 * @return
	 */
	List<String> getTouchableWeixinOpenIds();

	/**
	 * 获取与微信公众号进行过互动全部微信用户openId
	 * 
	 * @return
	 */
	List<String> getWeixinOpenIds();
}
