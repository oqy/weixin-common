package com.minyisoft.webapp.weixin.common.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Setter;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Joiner;

/**
 * @author qingyong_ou 微信接收消息真实性验证拦截器
 */
public class WeixinMessageReceiveInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Setter
	private String weixinToken;

	/*
	 * 加密/校验流程如下： 1. 将token、timestamp、nonce三个参数进行字典序排序 2.
	 * 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String[] sources = { request.getParameter("timestamp"), request.getParameter("nonce"), weixinToken };
		Arrays.sort(sources);
		if (!DigestUtils.sha1Hex(Joiner.on("").join(sources)).equals(request.getParameter("signature"))) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			logger.error("不通过签名认证，并非来自微信的请求");
			return false;
		}
		return true;
	}
}
