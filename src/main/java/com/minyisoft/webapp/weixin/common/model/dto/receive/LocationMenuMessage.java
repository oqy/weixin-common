package com.minyisoft.webapp.weixin.common.model.dto.receive;

import lombok.Getter;
import lombok.Setter;

import com.minyisoft.webapp.weixin.common.model.dto.receive.messagenode.SendLocationInfo;

/**
 * @author qingyong_ou 弹出地理位置选择器消息
 */
@Getter
@Setter
public class LocationMenuMessage extends MenuMessage {
	private SendLocationInfo sendLocationInfo;
}
