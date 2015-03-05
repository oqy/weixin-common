package com.minyisoft.webapp.weixin.mp.dto.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qingyong_ou 实体按钮基类
 */
@Getter
@Setter
public abstract class EntityButton extends AbstractButton {
	// 菜单的响应动作类型
	private String type;
}
