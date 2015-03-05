package com.minyisoft.webapp.weixin.mp.dto.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;
import com.minyisoft.webapp.weixin.mp.dto.menu.ButtonGroup;
import com.minyisoft.webapp.weixin.mp.dto.menu.EntityButton;
import com.minyisoft.webapp.weixin.mp.dto.menu.InteractButton;
import com.minyisoft.webapp.weixin.mp.dto.menu.Menu;
import com.minyisoft.webapp.weixin.mp.dto.menu.ViewButton;

public class MenuTest {
	private Menu menu = new Menu();

	@Before
	public void prepareMenu() {
		menu.appendButton(new ButtonGroup("按钮组1").appendButton(new InteractButton("按钮1", "click", "product"))
				.appendButton(new InteractButton("按钮2", "click", "news"))
				.appendButton(new InteractButton("按钮3", "click", "company")));
		menu.appendButton(new ViewButton("按钮4", "http://www.sina.com.cn"));
		menu.appendButton(new ButtonGroup("按钮组5").appendButton(new InteractButton("按钮6", "click", "spotBill"))
				.appendButton(new InteractButton("按钮7", "click", "storage"))
				.appendButton(new InteractButton("按钮8", "click", "commodityCode")));
	}

	@Test
	public void testGetButton() {
		Optional<EntityButton> button = menu.getButton("spotBill");
		assertTrue(button.isPresent());
		assertEquals("click", button.get().getType());
		assertEquals("按钮6", button.get().getName());

		button = menu.getButton("http://www.sina.com.cn");
		assertTrue(button.isPresent());
		assertEquals("view", button.get().getType());
		assertEquals("按钮4", button.get().getName());
	}
}
