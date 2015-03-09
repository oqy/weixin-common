package com.minyisoft.webapp.weixin.mp.dto.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou
 */
@Getter
@Setter
public class ViewButton extends EntityButton {
	// 网页链接，用户点击菜单可打开链接，不超过256字节
	private String url;

	public ViewButton() {
		setType("view");
	}

	public ViewButton(String name, String url) {
		this();
		setName(name);
		setUrl(url);
	}
}
