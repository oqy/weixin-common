package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 弹出系统拍照发图/弹出拍照或者相册发图/弹出微信相册发图器
 */
@Getter
@Setter
public class PicMenuMessage extends MenuMessage {
	private SendPicsInfo sendPicsInfo;

	@Override
	protected void fillProperty(Map<String, Object> properties) {
		super.fillProperty(properties);
		setSendPicsInfo((SendPicsInfo) properties.get("sendPicsInfo"));
	}

	@Getter
	@Setter
	public class SendPicsInfo {
		private int count;
		private List<PicItem> picList;
	}

	@Getter
	@Setter
	public class PicItem {
		private String picMd5Sum;
	}
}
