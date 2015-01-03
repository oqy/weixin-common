package com.minyisoft.webapp.weixin.common.model.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 微信菜单对象
 * Created by neil on 15-1-3.
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
}
