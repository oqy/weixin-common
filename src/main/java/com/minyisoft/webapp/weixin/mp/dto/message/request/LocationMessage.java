package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 地理位置消息
 */
@Getter
@Setter
public class LocationMessage extends CommonMessage {
	private BigDecimal location_X = BigDecimal.ZERO;
	private BigDecimal location_Y = BigDecimal.ZERO;
	private BigDecimal scale = BigDecimal.ZERO;
	private String label;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		if (properties.containsKey("location_X")) {
			setLocation_X(new BigDecimal((String) properties.get("location_X")));
		}
		if (properties.containsKey("location_Y")) {
			setLocation_Y(new BigDecimal((String) properties.get("location_Y")));
		}
		if (properties.containsKey("scale")) {
			setScale(new BigDecimal((String) properties.get("scale")));
		}
		setLabel((String) properties.get("label"));
	}
}
