package com.minyisoft.webapp.weixin.mp.dto.menu;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * @author qingyong_ou 菜单按钮组
 */
@Getter
@Setter
public class ButtonGroup extends AbstractButton {
	@JsonProperty("sub_button")
	private List<EntityButton> subButtons;

	public ButtonGroup() {

	}

	public ButtonGroup(String name) {
		setName(name);
	}

	public ButtonGroup appendButton(EntityButton button) {
		if (button != null) {
			if (subButtons == null) {
				subButtons = Lists.newArrayList();
			}
			subButtons.add(button);
		}
		return this;
	}
}
