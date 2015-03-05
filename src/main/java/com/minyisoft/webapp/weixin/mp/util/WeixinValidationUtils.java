package com.minyisoft.webapp.weixin.mp.util;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

public final class WeixinValidationUtils {
	private WeixinValidationUtils() {

	}

	/**
	 * 验证微信服务器地址有效性， 加密/校验流程如下： 1. 将token、timestamp、nonce三个参数进行字典序排序 2.
	 * 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @param signature
	 * @return
	 */
	public static boolean isSignatureValid(String timestamp, String nonce, String token, String signature) {
		if (StringUtils.isNotBlank(timestamp) && StringUtils.isNotBlank(nonce) && StringUtils.isNotBlank(token)
				&& StringUtils.isNotBlank(signature)) {
			String[] sources = { timestamp, nonce, token };
			Arrays.sort(sources);
			return DigestUtils.sha1Hex(Joiner.on("").join(sources)).equals(signature);
		}
		return false;
	}
}
