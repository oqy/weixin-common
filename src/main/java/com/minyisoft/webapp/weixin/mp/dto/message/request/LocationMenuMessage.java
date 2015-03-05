package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 弹出地理位置选择器
 */
@Getter
@Setter
public class LocationMenuMessage extends MenuMessage {
	private SendLocationInfo sendLocationInfo;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setSendLocationInfo((SendLocationInfo) properties.get("sendLocationInfo"));
	}

	@Getter
	@Setter
	public class SendLocationInfo {
		private BigDecimal location_X = BigDecimal.ZERO;
		private BigDecimal location_Y = BigDecimal.ZERO;
		private BigDecimal scale = BigDecimal.ZERO;
		private String label;
		private String poiname;
	}
}
