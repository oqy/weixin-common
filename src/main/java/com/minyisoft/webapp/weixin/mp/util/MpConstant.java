package com.minyisoft.webapp.weixin.mp.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author qingyong_ou 七牛配置信息
 */
public final class MpConstant {
	private MpConstant() {

	}

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(MpConstant.class.getResourceAsStream("mpConfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	// 微信内置浏览器User-Agent标示
	public static final String BROWSER_USER_AGENT_TAG = properties.getProperty("weixin.browser_user_agent_tag");

	public static final String ACCESS_TOKEN_URL = properties.getProperty("weixin.access_token_url");

	public static final String OAUTH2_ACCESS_TOKEN_URL = properties.getProperty("weixin.oauth2.access_token_url");

	// 客服消息
	public static final String SEND_MESSAGE_URL = properties.getProperty("weixin.send_message_url");

	public static final String SEND_TEMPLATE_MESSAGE_URL = properties.getProperty("weixin.send_template_message_url");

	// 用户管理
	public static final String USER_INFO_URL = properties.getProperty("weixin.user_info_url");

	public static final String USER_FETCH_URL = properties.getProperty("weixin.user_fetch_url");

	public static final String USER_FETCH_NEXT_URL = properties.getProperty("weixin.user_fetch_next_url");

	public static final String GROUP_CREATE_URL = properties.getProperty("weixin.group_create_url");

	public static final String GROUP_FETCH_URL = properties.getProperty("weixin.group_fetch_url");

	public static final String USER_GROUP_QUERY_URL = properties.getProperty("weixin.user_group_query_url");

	public static final String GROUP_UPDATE_URL = properties.getProperty("weixin.group_update_url");

	public static final String USER_GROUP_UPDATE_URL = properties.getProperty("weixin.user_group_update_url");

	public static final String USER_REMARK_UPDATE_URL = properties.getProperty("weixin.user_remark_update_url");

	// 自定义菜单
	public static final String CREATE_MENU_URL = properties.getProperty("weixin.create_menu_url");

	public static final String DELETE_MENU_URL = properties.getProperty("weixin.delete_menu_url");

	// jssdk
	public static final String JS_API_TICKET_URL = properties.getProperty("weixin.jsapi_ticket_url");

	// media
	public static final String MEDIA_UPLOAD_URL = properties.getProperty("weixin.media_upload_url");

	public static final String MEDIA_DOWNLOAD_URL = properties.getProperty("weixin.media_download_url");

	public static final String WEIXIN_OPEN_ID_VAR_NAME = "weixinOpenId";

	public static final String WEIXIN_TICKET_VAR_NAME = "weixinTicket";
}
