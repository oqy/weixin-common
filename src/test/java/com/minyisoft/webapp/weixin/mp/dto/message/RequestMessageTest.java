package com.minyisoft.webapp.weixin.mp.dto.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.minyisoft.webapp.weixin.mp.dto.message.request.EventMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.EventType;
import com.minyisoft.webapp.weixin.mp.dto.message.request.ImageMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.LinkMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.LocationMenuMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.LocationMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.MenuMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.MessageType;
import com.minyisoft.webapp.weixin.mp.dto.message.request.PicMenuMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.QrsceneMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.RequestMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.ScanCodeMenuMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.SubscribeMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.TemplateSendJobFinishMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.TextMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.UploadLocationMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.VideoMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.VoiceMessage;
import com.minyisoft.webapp.weixin.mp.dto.message.request.VoiceRecognitionMessage;
import com.minyisoft.webapp.weixin.mp.util.MessageMapper;

public class RequestMessageTest {

	@Test
	public void testTextMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>" + "<Content><![CDATA[this is a test]]></Content>"
				+ "<MsgId>1234567890123456</MsgId>" + "</xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof TextMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1348831860);
		assertEquals(message.getMsgType(), MessageType.TEXT);
		assertEquals(((TextMessage) message).getContent(), "this is a test");
		assertEquals(((TextMessage) message).getMsgId(), 1234567890123456L);
	}

	@Test
	public void testImageMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[image]]></MsgType>" + "<PicUrl><![CDATA[this is a url]]></PicUrl>"
				+ "<MediaId><![CDATA[media_id]]></MediaId>" + "<MsgId>1234567890123456</MsgId>" + "</xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof ImageMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1348831860);
		assertEquals(message.getMsgType(), MessageType.IMAGE);
		assertEquals(((ImageMessage) message).getPicUrl(), "this is a url");
		assertEquals(((ImageMessage) message).getMediaId(), "media_id");
		assertEquals(((ImageMessage) message).getMsgId(), 1234567890123456L);
	}

	@Test
	public void testVoiceMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[voice]]></MsgType>" + "<MediaId><![CDATA[media_id]]></MediaId>"
				+ "<Format><![CDATA[Format]]></Format>" + "<MsgId>1234567890123456</MsgId>" + "</xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof VoiceMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1348831860);
		assertEquals(message.getMsgType(), MessageType.VOICE);
		assertEquals(((VoiceMessage) message).getFormat(), "Format");
		assertEquals(((VoiceMessage) message).getMediaId(), "media_id");
		assertEquals(((VoiceMessage) message).getMsgId(), 1234567890123456L);
	}

	@Test
	public void testVideoMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[video]]></MsgType>" + "<MediaId><![CDATA[media_id]]></MediaId>"
				+ "<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>" + "<MsgId>1234567890123456</MsgId>"
				+ "</xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof VideoMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1348831860);
		assertEquals(message.getMsgType(), MessageType.VIDEO);
		assertEquals(((VideoMessage) message).getThumbMediaId(), "thumb_media_id");
		assertEquals(((VideoMessage) message).getMediaId(), "media_id");
		assertEquals(((VideoMessage) message).getMsgId(), 1234567890123456L);
	}

	@Test
	public void testLocationMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[location]]></MsgType>" + "<Location_X>23.134521</Location_X>"
				+ "<Location_Y>113.358803</Location_Y>" + "<Scale>20</Scale>"
				+ "<Label><![CDATA[位置信息]]></Label><MsgId>1234567890123456</MsgId>" + "</xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof LocationMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1348831860);
		assertEquals(message.getMsgType(), MessageType.LOCATION);
		assertEquals(((LocationMessage) message).getLocation_X(), new BigDecimal("23.134521"));
		assertEquals(((LocationMessage) message).getLocation_Y(), new BigDecimal("113.358803"));
		assertEquals(((LocationMessage) message).getScale(), new BigDecimal("20"));
		assertEquals(((LocationMessage) message).getLabel(), "位置信息");
		assertEquals(((LocationMessage) message).getMsgId(), 1234567890123456L);
	}

	@Test
	public void testLinkMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[link]]></MsgType>" + "<Title><![CDATA[公众平台官网链接]]></Title>"
				+ "<Description><![CDATA[公众平台官网链接]]></Description>" + "<Url><![CDATA[url]]></Url>"
				+ "<MsgId>1234567890123456</MsgId></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof LinkMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1348831860);
		assertEquals(message.getMsgType(), MessageType.LINK);
		assertEquals(((LinkMessage) message).getTitle(), "公众平台官网链接");
		assertEquals(((LinkMessage) message).getDescription(), "公众平台官网链接");
		assertEquals(((LinkMessage) message).getUrl(), "url");
		assertEquals(((LinkMessage) message).getMsgId(), 1234567890123456L);
	}

	@Test
	public void testSubscribeMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_355f34a3dc9b]]></ToUserName>"
				+ "<FromUserName><![CDATA[oqhfDjoxrlKbdPL3bh-jyllbjfz0]]></FromUserName>"
				+ "<CreateTime>1406885091</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[subscribe]]></Event></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof SubscribeMessage);
		assertEquals(message.getToUserName(), "gh_355f34a3dc9b");
		assertEquals(message.getFromUserName(), "oqhfDjoxrlKbdPL3bh-jyllbjfz0");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1406885091);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((SubscribeMessage) message).getEvent(), EventType.SUBSCRIBE);
		assertNull(((SubscribeMessage) message).getEventKey());
		assertNull(((SubscribeMessage) message).getTicket());

		xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>" + "<FromUserName><![CDATA[FromUser]]></FromUserName>"
				+ "<CreateTime>123456789</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[subscribe]]></Event>" + "<EventKey><![CDATA[qrscene_123123]]></EventKey>"
				+ "<Ticket><![CDATA[TICKET]]></Ticket></xml>";
		message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof SubscribeMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "FromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 123456789);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((SubscribeMessage) message).getEvent(), EventType.SUBSCRIBE);
		assertEquals(((SubscribeMessage) message).getEventKey(), "qrscene_123123");
		assertEquals(((SubscribeMessage) message).getTicket(), "TICKET");
	}

	@Test
	public void testUnsubscribeMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_355f34a3dc9b]]></ToUserName>"
				+ "<FromUserName><![CDATA[oqhfDjoxrlKbdPL3bh-jyllbjfz0]]></FromUserName>"
				+ "<CreateTime>1406885091</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[unsubscribe]]></Event></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof EventMessage);
		assertEquals(message.getToUserName(), "gh_355f34a3dc9b");
		assertEquals(message.getFromUserName(), "oqhfDjoxrlKbdPL3bh-jyllbjfz0");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1406885091);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((EventMessage) message).getEvent(), EventType.UNSUBSCRIBE);
	}

	@Test
	public void testQrsceneMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[FromUser]]></FromUserName>" + "<CreateTime>123456789</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>" + "<Event><![CDATA[SCAN]]></Event>"
				+ "<EventKey><![CDATA[SCENE_VALUE]]></EventKey>" + "<Ticket><![CDATA[TICKET]]></Ticket></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof QrsceneMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "FromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 123456789);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((QrsceneMessage) message).getEvent(), EventType.SCAN);
		assertEquals(((QrsceneMessage) message).getEventKey(), "SCENE_VALUE");
		assertEquals(((QrsceneMessage) message).getTicket(), "TICKET");
	}

	@Test
	public void testUploadLocationMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>123456789</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>" + "<Event><![CDATA[LOCATION]]></Event>"
				+ "<Latitude>23.137466</Latitude>" + "<Longitude>113.352425</Longitude>"
				+ "<Precision>119.385040</Precision></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof UploadLocationMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 123456789);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((UploadLocationMessage) message).getEvent(), EventType.LOCATION);
		assertEquals(((UploadLocationMessage) message).getLatitude(), new BigDecimal("23.137466"));
		assertEquals(((UploadLocationMessage) message).getLongitude(), new BigDecimal("113.352425"));
		assertEquals(((UploadLocationMessage) message).getPrecision(), new BigDecimal("119.385040"));
	}

	@Test
	public void testClickMenuMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_355f34a3dc9b]]></ToUserName>"
				+ "<FromUserName><![CDATA[oqhfDjoxrlKbdPL3bh-jyllbjfz0]]></FromUserName>"
				+ "<CreateTime>1406885091</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[CLICK]]></Event>" + "<EventKey><![CDATA[internal_supply]]></EventKey></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof MenuMessage);
		assertEquals(message.getToUserName(), "gh_355f34a3dc9b");
		assertEquals(message.getFromUserName(), "oqhfDjoxrlKbdPL3bh-jyllbjfz0");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1406885091);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((MenuMessage) message).getEvent(), EventType.CLICK);
		assertEquals(((MenuMessage) message).getEventKey(), "internal_supply");
	}

	@Test
	public void testViewMenuMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[FromUser]]></FromUserName>" + "<CreateTime>123456789</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>" + "<Event><![CDATA[VIEW]]></Event>"
				+ "<EventKey><![CDATA[www.qq.com]]></EventKey></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof MenuMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "FromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 123456789);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((MenuMessage) message).getEvent(), EventType.VIEW);
		assertEquals(((MenuMessage) message).getEventKey(), "www.qq.com");
	}

	@Test
	public void testVoiceRecognitionMessage() {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1357290913</CreateTime>"
				+ "<MsgType><![CDATA[voice]]></MsgType>" + "<MediaId><![CDATA[media_id]]></MediaId>"
				+ "<Format><![CDATA[Format]]></Format>" + "<Recognition><![CDATA[腾讯微信团队]]></Recognition>"
				+ "<MsgId>1234567890123456</MsgId></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof VoiceRecognitionMessage);
		assertEquals(message.getToUserName(), "toUser");
		assertEquals(message.getFromUserName(), "fromUser");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1357290913);
		assertEquals(message.getMsgType(), MessageType.VOICE);
		assertEquals(((VoiceRecognitionMessage) message).getMediaId(), "media_id");
		assertEquals(((VoiceRecognitionMessage) message).getFormat(), "Format");
		assertEquals(((VoiceRecognitionMessage) message).getRecognition(), "腾讯微信团队");
		assertEquals(((VoiceRecognitionMessage) message).getMsgId(), 1234567890123456L);
	}

	@Test
	public void testTemplateSendJobFinishMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>"
				+ "<FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>"
				+ "<CreateTime>1395658984</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>" + "<MsgID>200163840</MsgID>"
				+ "<Status><![CDATA[failed: system failed]]></Status></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof TemplateSendJobFinishMessage);
		assertEquals(message.getToUserName(), "gh_7f083739789a");
		assertEquals(message.getFromUserName(), "oia2TjuEGTNoeX76QEjQNrcURxG8");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1395658984);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((TemplateSendJobFinishMessage) message).getEvent(), EventType.TEMPLATESENDJOBFINISH);
		assertEquals(((TemplateSendJobFinishMessage) message).getMsgID(), 200163840);
		assertEquals(((TemplateSendJobFinishMessage) message).getStatus(), "failed: system failed");
	}

	@Test
	public void testScanCodePushMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>"
				+ "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>"
				+ "<CreateTime>1408090502</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[scancode_push]]></Event>" + "<EventKey><![CDATA[6]]></EventKey>"
				+ "<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>"
				+ "<ScanResult><![CDATA[1]]></ScanResult></ScanCodeInfo></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof ScanCodeMenuMessage);
		assertEquals(message.getToUserName(), "gh_e136c6e50636");
		assertEquals(message.getFromUserName(), "oMgHVjngRipVsoxg6TuX3vz6glDg");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1408090502);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((ScanCodeMenuMessage) message).getEvent(), EventType.SCANCODE_PUSH);
		assertEquals(((ScanCodeMenuMessage) message).getEventKey(), "6");
		assertEquals(((ScanCodeMenuMessage) message).getScanCodeInfo().getScanType(), "qrcode");
		assertEquals(((ScanCodeMenuMessage) message).getScanCodeInfo().getScanResult(), "1");
	}

	@Test
	public void testScanCodeWaitMsgMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>"
				+ "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>"
				+ "<CreateTime>1408090606</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[scancode_waitmsg]]></Event>" + "<EventKey><![CDATA[6]]></EventKey>"
				+ "<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>"
				+ "<ScanResult><![CDATA[2]]></ScanResult></ScanCodeInfo></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof ScanCodeMenuMessage);
		assertEquals(message.getToUserName(), "gh_e136c6e50636");
		assertEquals(message.getFromUserName(), "oMgHVjngRipVsoxg6TuX3vz6glDg");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1408090606);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((ScanCodeMenuMessage) message).getEvent(), EventType.SCANCODE_WAITMSG);
		assertEquals(((ScanCodeMenuMessage) message).getEventKey(), "6");
		assertEquals(((ScanCodeMenuMessage) message).getScanCodeInfo().getScanType(), "qrcode");
		assertEquals(((ScanCodeMenuMessage) message).getScanCodeInfo().getScanResult(), "2");
	}

	@Test
	public void testPicSysPhotoMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>"
				+ "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>"
				+ "<CreateTime>1408090651</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[pic_sysphoto]]></Event>"
				+ "<EventKey><![CDATA[6]]></EventKey><SendPicsInfo><Count>1</Count>"
				+ "<PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum></item></PicList></SendPicsInfo></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof PicMenuMessage);
		assertEquals(message.getToUserName(), "gh_e136c6e50636");
		assertEquals(message.getFromUserName(), "oMgHVjngRipVsoxg6TuX3vz6glDg");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1408090651);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((PicMenuMessage) message).getEvent(), EventType.PIC_SYSPHOTO);
		assertEquals(((PicMenuMessage) message).getEventKey(), "6");
		assertEquals(((PicMenuMessage) message).getSendPicsInfo().getCount(), 1);
		assertEquals(((PicMenuMessage) message).getSendPicsInfo().getPicList().get(0).getPicMd5Sum(),
				"1b5f7c23b5bf75682a53e7b6d163e185");
	}

	@Test
	public void testPicPhotoOrAlbumMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>"
				+ "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>"
				+ "<CreateTime>1408090816</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[pic_photo_or_album]]></Event>"
				+ "<EventKey><![CDATA[6]]></EventKey>"
				+ "<SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum></item></PicList></SendPicsInfo></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof PicMenuMessage);
		assertEquals(message.getToUserName(), "gh_e136c6e50636");
		assertEquals(message.getFromUserName(), "oMgHVjngRipVsoxg6TuX3vz6glDg");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1408090816);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((PicMenuMessage) message).getEvent(), EventType.PIC_PHOTO_OR_ALBUM);
		assertEquals(((PicMenuMessage) message).getEventKey(), "6");
		assertEquals(((PicMenuMessage) message).getSendPicsInfo().getCount(), 1);
		assertEquals(((PicMenuMessage) message).getSendPicsInfo().getPicList().get(0).getPicMd5Sum(),
				"5a75aaca956d97be686719218f275c6b");
	}

	@Test
	public void testPicWeixinMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>"
				+ "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>"
				+ "<CreateTime>1408090816</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[pic_weixin]]></Event>"
				+ "<EventKey><![CDATA[6]]></EventKey>"
				+ "<SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum></item></PicList></SendPicsInfo></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof PicMenuMessage);
		assertEquals(message.getToUserName(), "gh_e136c6e50636");
		assertEquals(message.getFromUserName(), "oMgHVjngRipVsoxg6TuX3vz6glDg");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1408090816);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((PicMenuMessage) message).getEvent(), EventType.PIC_WEIXIN);
		assertEquals(((PicMenuMessage) message).getEventKey(), "6");
		assertEquals(((PicMenuMessage) message).getSendPicsInfo().getCount(), 1);
		assertEquals(((PicMenuMessage) message).getSendPicsInfo().getPicList().get(0).getPicMd5Sum(),
				"5a75aaca956d97be686719218f275c6b");
	}

	@Test
	public void testLocationSelectMessage() {
		String xml = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>"
				+ "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>"
				+ "<CreateTime>1408091189</CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[location_select]]></Event>"
				+ "<EventKey><![CDATA[6]]></EventKey><SendLocationInfo><Location_X><![CDATA[23]]></Location_X>"
				+ "<Location_Y><![CDATA[113]]></Location_Y>" + "<Scale><![CDATA[15]]></Scale>"
				+ "<Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>"
				+ "<Poiname><![CDATA[]]></Poiname></SendLocationInfo></xml>";
		RequestMessage message = MessageMapper.fromXML(xml);
		assertTrue(message instanceof LocationMenuMessage);
		assertEquals(message.getToUserName(), "gh_e136c6e50636");
		assertEquals(message.getFromUserName(), "oMgHVjngRipVsoxg6TuX3vz6glDg");
		assertEquals(message.getCreateTime().getTime() / 1000L, 1408091189);
		assertEquals(message.getMsgType(), MessageType.EVENT);
		assertEquals(((LocationMenuMessage) message).getEvent(), EventType.LOCATION_SELECT);
		assertEquals(((LocationMenuMessage) message).getEventKey(), "6");
		assertEquals(((LocationMenuMessage) message).getSendLocationInfo().getLocation_X(), new BigDecimal("23"));
		assertEquals(((LocationMenuMessage) message).getSendLocationInfo().getLocation_Y(), new BigDecimal("113"));
		assertEquals(((LocationMenuMessage) message).getSendLocationInfo().getScale(), new BigDecimal("15"));
		assertEquals(((LocationMenuMessage) message).getSendLocationInfo().getLabel(), " 广州市海珠区客村艺苑路 106号");
		assertEquals(((LocationMenuMessage) message).getSendLocationInfo().getPoiname(), "");
	}
}
