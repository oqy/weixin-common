package com.minyisoft.webapp.weixin.common.model.dto.receive;

import lombok.Getter;
import lombok.Setter;

import com.minyisoft.webapp.weixin.common.model.dto.receive.messagenode.SendPicsInfo;

@Getter
@Setter
public class PicMenuMessage extends MenuMessage {
	private SendPicsInfo sendPicsInfo;
}
