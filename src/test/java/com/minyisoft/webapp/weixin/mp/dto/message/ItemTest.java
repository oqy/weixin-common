package com.minyisoft.webapp.weixin.mp.dto.message;

import org.apache.commons.lang3.StringUtils;

import com.minyisoft.webapp.weixin.mp.dto.message.request.PicMenuMessage.PicItem;
import com.minyisoft.webapp.weixin.mp.dto.message.request.PicMenuMessage.SendPicsInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class ItemTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xml = "<SendPicsInfo><Count>1</Count>"
				+ "<PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum></item></PicList></SendPicsInfo></xml>";
		XStream xstream = new XStream() {
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					public String realMember(@SuppressWarnings("rawtypes") Class type, String serialized) {
						return super.realMember(type, StringUtils.uncapitalize(serialized));
					};
				};
			};
		};
		xstream.alias("SendPicsInfo", SendPicsInfo.class);
		xstream.alias("item", PicItem.class);
		System.out.println(xstream.fromXML(xml));
	}

}
