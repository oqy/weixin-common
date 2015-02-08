package com.minyisoft.webapp.weixin.mp.service;

import static org.springframework.util.Assert.notNull;

import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import com.minyisoft.webapp.weixin.mp.dto.MpDevCredential;
import com.minyisoft.webapp.weixin.mp.dto.MpResponse;
import com.minyisoft.webapp.weixin.mp.dto.menu.Menu;
import com.minyisoft.webapp.weixin.mp.util.MessageMapper;
import com.minyisoft.webapp.weixin.mp.util.MpConstant;

/**
 * @author qingyong_ou 微信消息发送服务
 */
@Service
public class MpMenuService extends AbstractMpService {
	/**
	 * 创建自定义菜单
	 * 
	 * @param credential
	 * @param menu
	 * @return
	 */
	public MpResponse createMenu(MpDevCredential credential, Menu menu) {
		notNull(menu, "自定义菜单不能为空");

		String menuJsonStr = MessageMapper.toJson(menu);
		logger.info("创建自定义菜单：" + menuJsonStr);
		MpResponse response = getRestTemplate().postForObject(
				MessageFormat.format(MpConstant.CREATE_MENU_URL, getAccessToken(credential)), menuJsonStr,
				MpResponse.class);
		if (!response.isSuccessful()) {
			logger.error(MessageFormat.format("创建自定义菜单失败，AppID：{0},错误码：{1}", credential.getId(), response.getErrCode()));
		}
		return response;
	}

	/**
	 * 删除自定义菜单
	 * 
	 * @param credential
	 * @return
	 */
	public MpResponse deleteMenu(MpDevCredential credential) {
		MpResponse response = getRestTemplate().getForObject(
				MessageFormat.format(MpConstant.DELETE_MENU_URL, getAccessToken(credential)), MpResponse.class);
		if (!response.isSuccessful()) {
			logger.error(MessageFormat.format("删除自定义菜单失败，AppID：{0},错误码：{1}", credential.getId(), response.getErrCode()));
		}
		return response;
	}
}
