package com.minyisoft.webapp.weixin.mp.dto;

import static org.springframework.util.Assert.isTrue;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;


/**
 * @author qingyong_ou 微信信封，向微信发送请求时封装开发者标识和目标接收群体
 * 
 */
@Getter
@Setter
public class MpEnvelope {
	// 开发者标识
	private MpDevCredential credential;
	// 微信用户OpenId
	private String weixinOpenId;

	public MpEnvelope() {

	}

	public MpEnvelope(MpDevCredential credential, String weixinOpenId) {
		isTrue(credential != null && credential.isWellformed(), "无效的微信开发者凭据");
		this.credential = credential;
		this.weixinOpenId = weixinOpenId;
	}

	public boolean isCredentialWellformed() {
		return credential != null && credential.isWellformed();
	}

	public boolean isTargetDefined() {
		return StringUtils.isNoneBlank(weixinOpenId);
	}
}
