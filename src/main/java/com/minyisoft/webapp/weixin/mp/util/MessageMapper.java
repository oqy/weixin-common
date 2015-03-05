package com.minyisoft.webapp.weixin.mp.util;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.minyisoft.webapp.weixin.mp.dto.message.request.Message;
import com.minyisoft.webapp.weixin.mp.dto.message.request.MessageType;
import com.minyisoft.webapp.weixin.mp.dto.message.request.RequestMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.RequestMessageConverter;
import com.minyisoft.webapp.weixin.mp.dto.message.request.PicMenuMessage.PicItem;
import com.minyisoft.webapp.weixin.mp.dto.message.request.response.MessageTypeConverter;
import com.minyisoft.webapp.weixin.mp.dto.message.request.response.WeixinDateConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public final class MessageMapper {
	protected static Logger logger = LoggerFactory.getLogger(MessageMapper.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final XStream XML_REQUEST_MAPPER = new XStream() {
		protected MapperWrapper wrapMapper(MapperWrapper next) {
			return new MapperWrapper(next) {
				public String realMember(@SuppressWarnings("rawtypes") Class type, String serialized) {
					return super.realMember(type, StringUtils.uncapitalize(serialized));
				};
			};
		};
	};
	private static final XStream XML_RESPONSE_MAPPER = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				private boolean needCData;

				public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
					super.startNode(name, clazz);
					needCData = String.class.isAssignableFrom(clazz) || MessageType.class == clazz;
				}

				protected void writeText(QuickWriter writer, String text) {
					if (!needCData) {
						writer.write(text);
					} else {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}
				};
			};
		};
	}) {
		@Override
		protected MapperWrapper wrapMapper(MapperWrapper next) {
			return new MapperWrapper(next) {
				@Override
				public String serializedMember(@SuppressWarnings("rawtypes") Class type, String memberName) {
					return super.serializedMember(type, StringUtils.capitalize(memberName));
				}
			};
		};
	};

	static {
		XML_REQUEST_MAPPER.alias("xml", RequestMessage.class);
		XML_REQUEST_MAPPER.alias("item", PicItem.class);
		XML_REQUEST_MAPPER.registerConverter(new RequestMessageConverter());

		XML_RESPONSE_MAPPER.registerConverter(new WeixinDateConverter());
		XML_RESPONSE_MAPPER.registerConverter(new MessageTypeConverter());

		OBJECT_MAPPER.setSerializationInclusion(Include.NON_EMPTY);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	private MessageMapper() {

	}

	public static final RequestMessage fromXML(String xml) {
		return (RequestMessage) XML_REQUEST_MAPPER.fromXML(xml);
	}

	public static final String toXML(Message message) {
		XML_RESPONSE_MAPPER.alias("xml", message.getClass());
		return XML_RESPONSE_MAPPER.toXML(message);
	}

	public static String toJson(Object object, SerializationFeature... features) {
		try {
			if (ArrayUtils.isNotEmpty(features)) {
				return OBJECT_MAPPER.writer(features[0], features).writeValueAsString(object);
			}
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz, DeserializationFeature... features) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		}

		try {
			if (ArrayUtils.isNotEmpty(features)) {
				return OBJECT_MAPPER.reader(features[0], features).withType(clazz).readValue(jsonString);
			}
			return OBJECT_MAPPER.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		}

		try {
			return (T) OBJECT_MAPPER.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	public static TypeFactory getTypeFactory() {
		return OBJECT_MAPPER.getTypeFactory();
	}

	public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
}
