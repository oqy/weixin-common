package com.minyisoft.webapp.weixin.common.dto.receive;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 上报地理位置事件
 */
@Getter
@Setter
public class UploadLocationMessage extends EventMessage {
	private BigDecimal latitude;
	private BigDecimal longitude;
	private BigDecimal precision;

	@Override
	protected void fillProperty(Map<String, String> properties) {
		super.fillProperty(properties);
		if (properties.containsKey("latitude")) {
			setLatitude(new BigDecimal(properties.get("latitude")));
		}
		if (properties.containsKey("longitude")) {
			setLongitude(new BigDecimal(properties.get("longitude")));
		}
		if (properties.containsKey("precision")) {
			setPrecision(new BigDecimal(properties.get("precision")));
		}
	}
}
