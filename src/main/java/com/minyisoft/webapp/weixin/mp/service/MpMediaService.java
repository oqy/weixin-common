package com.minyisoft.webapp.weixin.mp.service;

import java.text.MessageFormat;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Optional;
import com.minyisoft.webapp.weixin.mp.dto.MpDevCredential;
import com.minyisoft.webapp.weixin.mp.dto.MpResponse;
import com.minyisoft.webapp.weixin.mp.dto.media.MediaMultipartFile;
import com.minyisoft.webapp.weixin.mp.dto.media.MediaResponseExtractor;
import com.minyisoft.webapp.weixin.mp.util.MpConstant;

@Service
public class MpMediaService extends AbstractMpService {
	private final MediaResponseExtractor responseExtractor = new MediaResponseExtractor();

	/**
	 * 下载多媒体资源文件
	 * 
	 * @param credential
	 * @param mediaId
	 * @return
	 */
	public Optional<MultipartFile> downloadMedia(MpDevCredential credential, String mediaId) {
		Object response = getRestTemplate().execute(
				MessageFormat.format(MpConstant.MEDIA_DOWNLOAD_URL, getAccessToken(credential), mediaId),
				HttpMethod.GET, null, responseExtractor);
		if (response instanceof MpResponse) {
			logger.error(MessageFormat.format("下载多媒体资源文件出错，AppID：{0},错误码：{1}", credential.getId(),
					((MpResponse) response).getErrCode()));
		} else if (response instanceof MediaMultipartFile) {
			return Optional.of((MultipartFile) response);
		}
		return Optional.absent();
	}
}
