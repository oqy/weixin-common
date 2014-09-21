package com.minyisoft.webapp.weixin.common.model.dto.receive;

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
}
