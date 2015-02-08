package com.minyisoft.webapp.weixin.mp.dto.message;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.minyisoft.webapp.weixin.mp.dto.message.request.MessageType;
import com.minyisoft.webapp.weixin.mp.dto.message.request.response.TransferCustomerServiceMessage;
import com.minyisoft.webapp.weixin.mp.util.MessageMapper;

public class ResponseMessageTest {

	@Test
	public void testTransferCustomerServiceMessage() {
		TransferCustomerServiceMessage message = new TransferCustomerServiceMessage();
		message.setToUserName("touser");
		message.setFromUserName("fromuser");
		message.setCreateTime(new Date(1399197672L * 1000L));
		message.setMsgType(MessageType.TRANSFER_CUSTOMER_SERVICE);
		assertEquals(
				MessageMapper.toXML(message).replaceAll("\\s", ""),
				"<xml><ToUserName><![CDATA[touser]]></ToUserName><FromUserName><![CDATA[fromuser]]></FromUserName><CreateTime>1399197672</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>");
	}
}
