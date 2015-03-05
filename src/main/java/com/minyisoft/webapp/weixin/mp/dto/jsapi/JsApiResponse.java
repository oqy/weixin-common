package com.minyisoft.webapp.weixin.mp.dto.jsapi;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minyisoft.webapp.weixin.mp.dto.MpResponse;

@Getter
@Setter
public class JsApiResponse extends MpResponse {
	@JsonProperty("ticket")
	private String ticket;
	@JsonProperty("expires_in")
	private int expiresSeconds;
}
