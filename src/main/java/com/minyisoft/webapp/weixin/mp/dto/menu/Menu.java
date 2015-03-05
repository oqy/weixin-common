package com.minyisoft.webapp.weixin.mp.dto.menu;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

/**
 * @author qingyong_ou 微信菜单对象
 */
@Getter
@Setter
public class Menu {
	@JsonProperty("button")
	private List<AbstractButton> buttons;

	public Menu appendButton(AbstractButton button) {
		if (button != null) {
			if (buttons == null) {
				buttons = Lists.newArrayList();
			}
			buttons.add(button);
		}
		return this;
	}

	/**
	 * 查找指定键值对应的按钮实例
	 * 
	 * @param eventKey
	 * @return
	 */
	public Optional<EntityButton> getButton(String eventKey) {
		if (StringUtils.isNoneBlank(eventKey) && buttons != null && !buttons.isEmpty()) {
			Optional<EntityButton> result = null;
			for (AbstractButton button : buttons) {
				result = _getButton(button, eventKey);
				if (result.isPresent()) {
					return result;
				}
			}
		}
		return Optional.absent();
	}

	private Optional<EntityButton> _getButton(AbstractButton button, String eventKey) {
		if (button instanceof InteractButton
				&& StringUtils.equalsIgnoreCase(((InteractButton) button).getKey(), eventKey)) {
			return Optional.of((EntityButton) button);
		} else if (button instanceof ViewButton
				&& StringUtils.equalsIgnoreCase(((ViewButton) button).getUrl(), eventKey)) {
			return Optional.of((EntityButton) button);
		} else if (button instanceof ButtonGroup && !CollectionUtils.isEmpty(((ButtonGroup) button).getSubButtons())) {
			Optional<EntityButton> result = null;
			for (EntityButton subButton : ((ButtonGroup) button).getSubButtons()) {
				result = _getButton(subButton, eventKey);
				if (result.isPresent()) {
					return result;
				}
			}
		}
		return Optional.absent();
	}
}
