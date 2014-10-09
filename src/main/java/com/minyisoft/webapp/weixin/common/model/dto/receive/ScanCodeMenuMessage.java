package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.minyisoft.webapp.weixin.common.model.dto.receive.messagenode.ScanCodeInfo;

/**
 * @author qingyong_ou 扫码推事件消息/扫码推事件且弹出“消息接收中”提示框消息
 */
@Getter
@Setter
public class ScanCodeMenuMessage extends MenuMessage {
	private ScanCodeInfo scanCodeInfo;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setScanCodeInfo((ScanCodeInfo) properties.get("scanCodeInfo"));
	}
}
