package com.minyisoft.webapp.weixin.common.model.dto.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * 实体按钮基类
 * Created by neil on 15-1-3.
 */
@Getter
@Setter
public abstract class EntityButton extends AbstractButton {
    // 菜单的响应动作类型
    private String type;
}
