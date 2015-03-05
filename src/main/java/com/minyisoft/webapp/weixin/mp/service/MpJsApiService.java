package com.minyisoft.webapp.weixin.mp.service;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.isTrue;

import java.text.MessageFormat;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.minyisoft.webapp.weixin.mp.dto.MpDevCredential;
import com.minyisoft.webapp.weixin.mp.dto.jsapi.JsApiConfig;
import com.minyisoft.webapp.weixin.mp.dto.jsapi.JsApiResponse;
import com.minyisoft.webapp.weixin.mp.util.MpConstant;

@Service
public class MpJsApiService extends AbstractMpService {
	private static final String _WEIXIN_JS_API_TICKET_KEY_FORMAT = "{0}:jsapi_ticket";// 微信jsapi_ticket在redis的键值
	private static final String _SIGNATURE_SOURCE_STRING_FORMAT = "jsapi_ticket={0}&noncestr={1}&timestamp={2,number,####}&url={3}";

	/**
	 * 获取JsApiTicket
	 * 
	 * @param credential
	 * @return
	 */
	public String getJsApiTicket(MpDevCredential credential) {
		isTrue(credential != null && credential.isWellformed(), "无效的微信开发者凭据");

		String jsApiTicketKey = MessageFormat.format(_WEIXIN_JS_API_TICKET_KEY_FORMAT, credential.getId());
		Optional<String> jsApiTicket = null;
		if ((jsApiTicket = getCacheValue(jsApiTicketKey)).isPresent()) {
			return jsApiTicket.get();
		}
		JsApiResponse response = getRestTemplate()
				.getForObject(MessageFormat.format(MpConstant.JS_API_TICKET_URL, getAccessToken(credential)),
						JsApiResponse.class);
		if (response.isSuccessful()) {
			// 缓存access_token，过期时间比微信官方时间短1分钟
			setCacheValue(jsApiTicketKey, response.getTicket(), response.getExpiresSeconds() - 60);
			return response.getTicket();
		} else {
			String errorMsg = MessageFormat.format("获取微信jsapi_ticket失败，错误码：{0}，错误提示：{1}", response.getErrCode(),
					response.getErrMsg());
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}
	}

	@Override
	public String getAccessToken(MpDevCredential credential) {
		return super.getAccessToken(credential);
	}

	/**
	 * 获取JsApiConfig
	 * 
	 * @param credential
	 * @param url
	 * @return
	 */
	public JsApiConfig getJsApiConfig(MpDevCredential credential, String url) {
		hasLength(url);

		JsApiConfig config = new JsApiConfig();
		config.setAppId(credential.getId());
		config.setNonceStr(UUID.randomUUID().toString());
		config.setTimestamp(System.currentTimeMillis() / 1000);
		config.setSignature(DigestUtils.sha1Hex(MessageFormat.format(_SIGNATURE_SOURCE_STRING_FORMAT,
				getJsApiTicket(credential), config.getNonceStr(), config.getTimestamp(), url)));
		return config;
	}
}
