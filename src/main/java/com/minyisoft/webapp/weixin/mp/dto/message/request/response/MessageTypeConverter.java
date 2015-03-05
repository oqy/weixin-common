package com.minyisoft.webapp.weixin.mp.dto.message.request.response;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.minyisoft.webapp.weixin.mp.dto.message.request.MessageType;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MessageTypeConverter implements Converter {

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		return MessageType.class == type;
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		writer.setValue(((MessageType) source).getValue());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return EnumUtils.getEnum(MessageType.class, StringUtils.upperCase(reader.getValue()));
	}

}
