package com.minyisoft.webapp.weixin.common.model.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minyisoft.webapp.core.utils.mapper.json.JsonMapper;

import java.io.IOException;

/**
 * Created by neil on 15-1-3.
 */
public class MenuTest {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.appendButton(new ButtonGroup("按钮组1").appendButton(new ViewButton("测试1","http://www.1.com")).appendButton(new InteractButton("测试2","click","test_2")));
        menu.appendButton(new InteractButton("测试3","click","test_2"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "@class");// default to using DefaultTyping.OBJECT_AND_NON_CONCRETE
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        String menuJsonStr = mapper.writeValueAsString(menu);
        System.out.println(menuJsonStr);
        Menu menu2 = mapper.readValue(menuJsonStr, Menu.class);
        System.out.println(JsonMapper.NON_EMPTY_MAPPER.toJson(menu));
        System.out.println(JsonMapper.NON_EMPTY_MAPPER.toJson(menu2));
    }
}
