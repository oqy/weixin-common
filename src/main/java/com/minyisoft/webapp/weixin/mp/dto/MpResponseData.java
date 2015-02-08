package com.minyisoft.webapp.weixin.mp.dto;

import static org.springframework.util.Assert.notNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 微信操作返回结果
 */
@Getter
@Setter
public class MpResponseData<T> extends MpResponse {
	private T data;

	public MpResponseData() {

	}

	public MpResponseData(MpResponse response) {
		notNull(response);
		setErrCode(response.getErrCode());
		setErrMsg(response.getErrMsg());
	}

	public MpResponseData(T data) {
		notNull(data);
		setErrCode("0");
		setData(data);
	}
}
