package com.minyisoft.webapp.weixin.mp.dto.media;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MediaType {
	IMAGE, VOICE, VIDEO, THUMB;

	@JsonCreator
	public static MediaType getType(String type) {
		return EnumUtils.getEnum(MediaType.class, StringUtils.upperCase(type));
	}
}
