package com.minyisoft.webapp.weixin.mp.dto.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 菜单按钮基类对象
 */
@Getter
@Setter
public abstract class AbstractButton {
	// 菜单标题，不超过16个字节，子菜单不超过40个字节
	private String name;
}
