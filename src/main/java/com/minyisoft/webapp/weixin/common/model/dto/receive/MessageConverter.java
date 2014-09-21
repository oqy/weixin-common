package com.minyisoft.webapp.weixin.common.model.dto.receive;

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MessageConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return Message.class.isAssignableFrom(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		try {
			Map<String, Object> map = (Map<String, Object>) PropertyUtils.describe(source);
			for (String key : map.keySet()) {
				addNode(writer, StringUtils.capitalize(key), map.get(key));
			}
		} catch (Exception e) {
		}
	}

	protected void addNode(HierarchicalStreamWriter writer, String nodeName, Object value) {
		if (!nodeName.equalsIgnoreCase("class") && value != null) {
			writer.startNode(nodeName);
			if (value instanceof Date) {
				writer.setValue(String.valueOf(((Date) value).getTime() / 1000L));
			} else {
				writer.setValue(String.valueOf(value));
			}
			writer.endNode();
		}
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Map<String, String> map = Maps.newHashMap();
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			map.put(StringUtils.uncapitalize(reader.getNodeName()), reader.getValue());
			reader.moveUp();
		}
		MessageType messageType = MessageType.valueOf(StringUtils.upperCase(map.get("msgType")));
		Message message = null;
		switch (messageType) {
		case TEXT:
			message = new TextMessage();
			break;
		case IMAGE:
			message = new ImageMessage();
			break;
		case VOICE:
			if (map.containsKey("recognition")) {
				message = new VoiceRecognitionMessage();
			} else {
				message = new VoiceMessage();
			}
			break;
		case VIDEO:
			message = new VideoMessage();
			break;
		case LOCATION:
			message = new LocationMessage();
			break;
		case LINK:
			message = new LinkMessage();
			break;
		case EVENT:
			EventType eventType = null;
			try{
				eventType = EventType.valueOf(StringUtils.upperCase(map.get("event")));
			} catch (Exception e) {
			}
			if (eventType != null) {
				switch (eventType) {
				case SUBSCRIBE:
				case UNSUBSCRIBE:
					message = new EventMessage();
					break;
				case SCAN:
					message = new QrsceneMessage();
					break;
				case LOCATION:
					message = new UploadLocationMessage();
					break;
				case CLICK:
				case VIEW:
					message = new MenuMessage();
					break;
				case TEMPLATESENDJOBFINISH:
					message = new TemplateSendJobFinishMessage();
					break;
				case SCANCODE_PUSH:
				case SCANCODE_WAITMSG:
					message = new ScanCodeMenuMessage();
					break;
				case PIC_PHOTO_OR_ALBUM:
				case PIC_SYSPHOTO:
				case PIC_WEIXIN:
					message = new PicMenuMessage();
					break;
				case LOCATION_SELECT:
					message = new LocationMenuMessage();
					break;
				default:
					break;
				}
			}
			if (message instanceof EventMessage) {
				((EventMessage) message).setEvent(eventType);
			}
			break;
		default:
			break;
		}
		if (message instanceof Message) {
			message.setMsgType(messageType);
			message.fillProperty(map);
		}
		return message;
	}

}
