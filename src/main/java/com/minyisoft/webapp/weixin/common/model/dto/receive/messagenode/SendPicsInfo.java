package com.minyisoft.webapp.weixin.common.model.dto.receive.messagenode;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendPicsInfo {
	private int count;
	private List<PicItem> picList;

	@Getter
	@Setter
	class PicItem {
		private String picMd5Sum;
	}
}
