package com.minyisoft.webapp.weixin.mp.dto.message.send;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
	private String title;
	private String description;
	private String URL;
	private String picurl;

	public Article() {

	}

	public Article(String title, String description, String url, String picUrl) {
		this.title = title;
		this.description = description;
		this.URL = url;
		this.picurl = picUrl;
	}
}
