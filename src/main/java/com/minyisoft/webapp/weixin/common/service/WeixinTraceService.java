package com.minyisoft.webapp.weixin.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.minyisoft.webapp.weixin.common.model.WeixinUserInfo;
import com.minyisoft.webapp.weixin.common.model.WeixinUserTraceInfo;
import com.minyisoft.webapp.weixin.common.model.dto.receive.EventMessage;
import com.minyisoft.webapp.weixin.common.model.dto.receive.EventType;
import com.minyisoft.webapp.weixin.common.model.dto.receive.MenuMessage;
import com.minyisoft.webapp.weixin.common.model.dto.receive.Message;
import com.minyisoft.webapp.weixin.common.model.dto.receive.TextMessage;
import com.minyisoft.webapp.weixin.common.persistence.WeixinUserDao;
import com.minyisoft.webapp.weixin.common.persistence.WeixinUserTraceDao;

/**
 * @author qingyong_ou 微信用户交互跟踪服务
 */
@Service
public class WeixinTraceService {
	@Autowired
	private WeixinCommonService weixinCommonService;
	@Autowired
	private WeixinUserTraceDao weixinUserTraceDao;
	@Autowired
	private WeixinUserDao weixinUserDao;

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

		// 保存微信用户基本信息
		if (weixinUserDao.getWeixinUser(trace.getWeixinOpenId()) == null) {
			Optional<WeixinUserInfo> user = weixinCommonService.queryWeixinUserInfo(trace.getWeixinOpenId());
			if (user.isPresent()) {
				weixinUserDao.insertWeixinUser(user.get());
			}
		}
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
