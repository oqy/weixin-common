package com.minyisoft.webapp.weixin.mp.dto;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 微信公众平台/企业号开发者凭据
 */
@Getter
@Setter
public class MpDevCredential {
	private String id;
	private String secret;

	public MpDevCredential() {

	}

	public MpDevCredential(String id, String secret) {
		this.id = id;
		this.secret = secret;
	}

	public boolean isWellformed() {
		return StringUtils.isNoneBlank(id) && StringUtils.isNotBlank(secret);
	}
}
