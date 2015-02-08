package com.minyisoft.webapp.weixin.mp.dto.media;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseExtractor;

import com.google.common.base.Charsets;
import com.minyisoft.webapp.weixin.mp.dto.MpResponse;
import com.minyisoft.webapp.weixin.mp.util.MessageMapper;

public class MediaResponseExtractor implements ResponseExtractor<Object> {
	private final StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charsets.UTF_8);

	private final static String AUDIO_CONTENT_TYPE = "audio/*".toLowerCase();

	@Override
	public Object extractData(ClientHttpResponse response) throws IOException {
		if (MediaType.IMAGE_JPEG.includes(response.getHeaders().getContentType())
				|| MediaType.parseMediaType(AUDIO_CONTENT_TYPE).includes(response.getHeaders().getContentType())) {
			return new MediaMultipartFile(response);
		} else if (stringHttpMessageConverter.canRead(String.class, response.getHeaders().getContentType())) {
			return MessageMapper.fromJson(
					stringHttpMessageConverter.read(String.class, (HttpInputMessage) response), MpResponse.class);
		}
		return null;
	}
}
