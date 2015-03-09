package com.minyisoft.webapp.weixin.mp.dto.message.request;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.minyisoft.webapp.weixin.mp.dto.message.request.LocationMenuMessage.SendLocationInfo;
import com.minyisoft.webapp.weixin.mp.dto.message.request.PicMenuMessage.SendPicsInfo;
import com.minyisoft.webapp.weixin.mp.dto.message.request.ScanCodeMenuMessage.ScanCodeInfo;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class RequestMessageConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return RequestMessage.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Map<String, Object> map = Maps.newHashMap();
		MessageType messageType = null;
		EventType eventType = null;
		Date createTime = null;
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if (StringUtils.equalsIgnoreCase(reader.getNodeName(), "MsgType")) {
				messageType = EnumUtils.getEnum(MessageType.class, StringUtils.upperCase(reader.getValue()));
			} else if (StringUtils.equalsIgnoreCase(reader.getNodeName(), "Event")) {
				eventType = EnumUtils.getEnum(EventType.class, StringUtils.upperCase(reader.getValue()));
			} else if (StringUtils.equalsIgnoreCase(reader.getNodeName(), "CreateTime")) {
				createTime = new Date(Long.parseLong(reader.getValue()) * 1000L);
			} else if (StringUtils.equalsIgnoreCase(reader.getNodeName(), "ScanCodeInfo")) {
				map.put("scanCodeInfo", context.convertAnother(new ScanCodeMenuMessage(), ScanCodeInfo.class));
			} else if (StringUtils.equalsIgnoreCase(reader.getNodeName(), "SendPicsInfo")) {
				map.put("sendPicsInfo", context.convertAnother(new PicMenuMessage(), SendPicsInfo.class));
			} else if (StringUtils.equalsIgnoreCase(reader.getNodeName(), "SendLocationInfo")) {
				map.put("sendLocationInfo", context.convertAnother(new LocationMenuMessage(), SendLocationInfo.class));
			} else {
				map.put(StringUtils.uncapitalize(reader.getNodeName()), reader.getValue());
			}
			reader.moveUp();
		}

		RequestMessage message = null;
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
			if (eventType != null) {
				switch (eventType) {
				case SUBSCRIBE:
					message = new SubscribeMessage();
					break;
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
				case SCANCODE_PUSH:
				case SCANCODE_WAITMSG:
					message = new ScanCodeMenuMessage();
					break;
				case PIC_SYSPHOTO:
				case PIC_PHOTO_OR_ALBUM:
				case PIC_WEIXIN:
					message = new PicMenuMessage();
					break;
				case LOCATION_SELECT:
					message = new LocationMenuMessage();
					break;
				case TEMPLATESENDJOBFINISH:
					message = new TemplateSendJobFinishMessage();
					break;
				default:
					break;
				}
			}
		default:
			break;
		}
		if (message != null) {
			message.setMsgType(messageType);
			message.setCreateTime(createTime);
			if (message instanceof EventMessage) {
				((EventMessage) message).setEvent(eventType);
			}
			message.fillProperty(map);
		}
		return message;
	}
}
