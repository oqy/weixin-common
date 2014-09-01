package com.minyisoft.webapp.weixin.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minyisoft.webapp.weixin.common.dto.WeixinUserTraceInfo;
import com.minyisoft.webapp.weixin.common.dto.receive.EventMessage;
import com.minyisoft.webapp.weixin.common.dto.receive.EventType;
import com.minyisoft.webapp.weixin.common.dto.receive.MenuMessage;
import com.minyisoft.webapp.weixin.common.dto.receive.Message;
import com.minyisoft.webapp.weixin.common.dto.receive.TextMessage;
import com.minyisoft.webapp.weixin.common.persistence.WeixinUserTraceDao;

/**
 * @author qingyong_ou 微信用户交互跟踪服务
 */
@Service
public class WeixinTraceService {
	@Autowired
	private WeixinUserTraceDao weixinUserTraceDao;

	/**
	 * 更新指定微信用户与公众号互动的最后时间，以及用户的操作轨迹
	 * 
	 * @param weixinOpenId
	 */
	public void traceWeixinUser(final Message message, final String messageString) {
		WeixinUserTraceInfo trace = new WeixinUserTraceInfo();
		trace.setWeixinOpenId(message.getFromUserName());
		trace.setTraceTime(message.getCreateTime());
		trace.setMessageType(message.getMsgType());
		if (message instanceof EventMessage) {
			trace.setEventType(((EventMessage) message).getEvent());
			if (message instanceof MenuMessage && ((MenuMessage) message).getEvent() == EventType.CLICK) {
				trace.setDescription("点击菜单[" + ((MenuMessage) message).getEventKey() + "]");
			}
		} else if (message instanceof TextMessage) {
			trace.setDescription(((TextMessage) message).getContent());
		}
		trace.setMessageString(messageString);
		weixinUserTraceDao.logTrace(trace);
	}

	/**
	 * 获取可进行交互（48小时内与微信公众号进行过互动）的微信用户openId集合
	 * 
	 * @return
	 */
	public List<String> getTouchableWeixinOpenIds() {
		return weixinUserTraceDao.getTouchableWeixinOpenIds();
	}
}
