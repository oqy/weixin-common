package com.minyisoft.webapp.weixin.mp.dto.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou
 */
@Getter
@Setter
public class InteractButton extends EntityButton {
	// 菜单KEY值，用于消息接口推送，不超过128字节
	private String key;

	public InteractButton() {

	}

	public InteractButton(String name, String type, String key) {
		setName(name);
		setType(type);
		this.key = key;
	}
}
