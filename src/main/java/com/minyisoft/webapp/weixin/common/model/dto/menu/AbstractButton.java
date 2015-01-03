package com.minyisoft.webapp.weixin.common.model.dto.menu;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单按钮基类对象
 * Created by neil on 15-1-3.
 */
@Getter
@Setter
public abstract class AbstractButton {
    // 菜单标题，不超过16个字节，子菜单不超过40个字节
    private String name;
}
