package com.minyisoft.webapp.weixin.common.model.dto.receive.messagenode;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendLocationInfo {
	private BigDecimal location_X = BigDecimal.ZERO;
	private BigDecimal location_Y = BigDecimal.ZERO;
	private BigDecimal scale = BigDecimal.ZERO;
	private String label;
	private String poiname;
}
