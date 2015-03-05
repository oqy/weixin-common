package com.minyisoft.webapp.weixin.mp.dto.user;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("group")
public class WeixinUserGroup {
	private int id;
	private String name;
	private Integer count;

	public WeixinUserGroup() {

	}

	public WeixinUserGroup(String name) {
		this.name = name;
	}

	public WeixinUserGroup(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
