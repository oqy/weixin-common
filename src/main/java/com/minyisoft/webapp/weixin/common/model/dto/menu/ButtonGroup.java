package com.minyisoft.webapp.weixin.common.model.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 菜单按钮组
 * Created by neil on 15-1-3.
 */
public class ButtonGroup extends AbstractButton {
    @JsonProperty("sub_button")
    private List<EntityButton> subButton;

    public ButtonGroup() {

    }

    public ButtonGroup(String name) {
        setName(name);
    }

    public ButtonGroup appendButton(EntityButton button) {
        if (button != null) {
            if (subButton == null) {
                subButton = Lists.newArrayList();
            }
            subButton.add(button);
        }
        return this;
    }
}
