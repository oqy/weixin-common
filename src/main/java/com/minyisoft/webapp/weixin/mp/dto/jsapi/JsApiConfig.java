package com.minyisoft.webapp.weixin.mp.dto.jsapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsApiConfig {
	private String appId;
	private long timestamp;
	private String nonceStr;
	private String signature;
}
