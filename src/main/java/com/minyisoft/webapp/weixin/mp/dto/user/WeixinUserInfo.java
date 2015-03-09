package com.minyisoft.webapp.weixin.mp.dto.user;

import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 微信用户基本信息
 */
@Getter
@Setter
public class WeixinUserInfo {
	// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	private int subscribe;
	// 用户的标识，对当前公众号唯一
	private String openid;
	// 用户的昵称
	private String nickname;
	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private int sex;
	// 用户的语言，简体中文为zh_CN
	private String language;
	// 用户所在城市
	private String city;
	// 用户所在国家
	private String country;
	// 用户所在省份
	private String province;
	// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	private String headimgurl;
	// 用户关注时间
	private Date subscribeTime;
	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	private String unionid;

	public String getSexDescription() {
		switch (sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "未知";
		}
	}

	/**
	 * 将微信以秒为基准的时间戳转为Date对象
	 * 
	 * @param timeStamp
	 */
	public void setSubscribe_time(Integer timeStamp) {
		setSubscribeTime(new Date(timeStamp.longValue() * 1000));
	}

	private static final int[] headimgSize = { 0, 46, 64, 96, 132 };

	public String getHeadImgSize(int size) {
		if (StringUtils.isNotBlank(headimgurl) && ArrayUtils.contains(headimgSize, size)) {
			return headimgurl.substring(0, headimgurl.lastIndexOf('/')) + "/" + size;
		}
		return headimgurl;
	}
}
