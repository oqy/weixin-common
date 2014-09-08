package com.minyisoft.webapp.weixin.common.persistence;

import com.minyisoft.webapp.weixin.common.model.WeixinUserInfo;

/**
 * @author qingyong_ou 微信用户操作跟踪dao
 */
public interface WeixinUserDao {
	/**
	 * 获取微信用户
	 * 
	 * @param weixinOpenId
	 * @return
	 */
	WeixinUserInfo getWeixinUser(String weixinOpenId);

	/**
	 * 新增微信用户基本信息
	 * 
	 * @param userInfoMap
	 * @return
	 */
	int insertWeixinUser(WeixinUserInfo weixinUser);

	/**
	 * 更新微信用户基本信息
	 * 
	 * @param userInfoMap
	 * @return
	 */
	int updateWeixinUser(WeixinUserInfo weixinUser);
}
