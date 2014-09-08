package com.minyisoft.webapp.weixin.common.model.dto.send;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomerServiceMessageSerilizer extends
		StdSerializer<CustomerServiceMessage> {

	protected CustomerServiceMessageSerilizer() {
		super(CustomerServiceMessage.class);
	}

	@Override
	public void serialize(CustomerServiceMessage value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeStartObject();
		jgen.writeStringField("touser", value.getToUser());
		jgen.writeStringField("msgtype", value.getMessageType().getValue());
		jgen.writeFieldName(value.getMessageType().getValue());
		jgen.writeStartObject();
		for (Field field : value.getClass().getDeclaredFields()) {
			try {
				jgen.writeFieldName(field.getName());
				field.setAccessible(true);
				jgen.writeObject(field.get(value));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		jgen.writeEndObject();
		jgen.writeEndObject();
	}

}
