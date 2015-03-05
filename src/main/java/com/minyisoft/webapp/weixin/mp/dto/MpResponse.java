package com.minyisoft.webapp.weixin.mp.dto;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author qingyong_ou 微信操作返回结果
 */
@Getter
@Setter
public class MpResponse {
	@JsonProperty("errcode")
	private String errCode;
	@JsonProperty("errmsg")
	private String errMsg;

	public boolean isSuccessful() {
		return "0".equals(errCode);
	}
}
