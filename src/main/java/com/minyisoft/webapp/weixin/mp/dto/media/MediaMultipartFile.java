package com.minyisoft.webapp.weixin.mp.dto.media;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class MediaMultipartFile implements MultipartFile {
	private byte[] bytes = null;
	private final String fileName;
	private final long length;
	private final String contentType;

	public MediaMultipartFile(ClientHttpResponse response) throws IOException {
		bytes = FileCopyUtils.copyToByteArray(response.getBody());
		fileName = StringUtils.substringBetween(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION),
				"filename=\"", "\"");
		length = response.getHeaders().getContentLength();
		contentType = response.getHeaders().getContentType().toString();
	}

	@Override
	public String getName() {
		return fileName;
	}

	@Override
	public String getOriginalFilename() {
		return fileName;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}

	@Override
	public long getSize() {
		return length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return bytes;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(bytes);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		FileCopyUtils.copy(getInputStream(), new FileOutputStream(dest));
	}

}
