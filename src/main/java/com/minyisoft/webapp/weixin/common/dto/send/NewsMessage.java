package com.minyisoft.webapp.weixin.common.dto.send;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsMessage extends CustomerServiceMessage {
	private List<Article> articles;

	@Override
	public CustomerServiceMessageType getMessageType() {
		return CustomerServiceMessageType.NEWS;
	}

}
