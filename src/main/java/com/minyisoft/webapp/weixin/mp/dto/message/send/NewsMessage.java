package com.minyisoft.webapp.weixin.mp.dto.message.send;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.google.common.collect.Lists;

@Getter
@Setter
public class NewsMessage {
	private List<Article> articles;

	public NewsMessage() {

	}

	public NewsMessage(List<Article> article) {
		this.articles = article;
	}

	public NewsMessage appendArticle(Article article) {
		if (articles == null) {
			articles = Lists.newArrayList();
		}
		articles.add(article);
		return this;
	}
}
