package com.minyisoft.webapp.weixin.mp.service;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.minyisoft.webapp.weixin.mp.dto.MpDevCredential;
import com.minyisoft.webapp.weixin.mp.dto.MpResponse;
import com.minyisoft.webapp.weixin.mp.dto.MpResponseData;
import com.minyisoft.webapp.weixin.mp.dto.user.WeixinUserGroup;
import com.minyisoft.webapp.weixin.mp.dto.user.WeixinUserInfo;
import com.minyisoft.webapp.weixin.mp.util.MessageMapper;
import com.minyisoft.webapp.weixin.mp.util.MpConstant;

@Service
public class MpUserService extends AbstractMpService {
	private final static String _NEXT_OPEN_ID_VAR_NAME = "next_openid";
	private final static String _DATA_VAR_NAME = "data";
	private final static String _OPEN_ID_VAR_NAME = "openid";

	/**
	 * 获取用户列表
	 * 
	 * @param credential
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MpResponseData<List<String>> getUserOpenIds(MpDevCredential credential) {
		isTrue(credential != null && credential.isWellformed());

		List<String> openIds = Lists.newArrayList();
		MpResponse response = null;
		String nextOpenId = null;
		boolean fetchAll = false;
		do {
			String result = getRestTemplate().getForObject(
					StringUtils.isBlank(nextOpenId) ? MessageFormat.format(MpConstant.USER_FETCH_URL,
							getAccessToken(credential)) : MessageFormat.format(MpConstant.USER_FETCH_NEXT_URL,
							getAccessToken(credential), nextOpenId), String.class);
			if (StringUtils.containsIgnoreCase(result, "errcode")) {
				response = MessageMapper.fromJson(result, MpResponse.class);
				if (response != null) {
					logger.error(MessageFormat.format("获取微信用户列表失败，错误码：{0}，错误提示：{1}", response.getErrCode(),
							response.getErrMsg()));
				}
				fetchAll = true;
			} else if (StringUtils.isNoneBlank(result)) {
				Map<String, Object> map = MessageMapper.fromJson(result,
						MessageMapper.createCollectionType(HashMap.class, String.class, Object.class));
				fetchAll = StringUtils.isBlank((String) map.get(_NEXT_OPEN_ID_VAR_NAME));
				nextOpenId = (String) map.get(_NEXT_OPEN_ID_VAR_NAME);
				if (map.containsKey(_DATA_VAR_NAME)) {
					openIds.addAll(((Map<String, List<String>>) map.get(_DATA_VAR_NAME)).get(_OPEN_ID_VAR_NAME));
				}
			} else {
				fetchAll = true;
			}
		} while (!fetchAll);
		if (response != null && openIds.isEmpty()) {
			return new MpResponseData<>(response);
		}
		return new MpResponseData<>(openIds);
	}

	/**
	 * 查询指定openId用户基本信息
	 * 
	 * @param credential
	 * @param openId
	 * @return
	 */
	public MpResponseData<WeixinUserInfo> getUserInfo(MpDevCredential credential, String openId) {
		isTrue(credential != null && credential.isWellformed());
		hasText(openId);

		String result = getRestTemplate().getForObject(
				MessageFormat.format(MpConstant.USER_INFO_URL, getAccessToken(credential), openId), String.class);
		if (StringUtils.containsIgnoreCase(result, "errcode")) {
			MpResponse response = MessageMapper.fromJson(result, MpResponse.class);
			logger.error(MessageFormat.format("获取微信用户基本信息失败，错误码：{0}，错误提示：{1}", response.getErrCode(),
					response.getErrMsg()));
			return new MpResponseData<WeixinUserInfo>(response);
		} else {
			return new MpResponseData<WeixinUserInfo>(MessageMapper.fromJson(result, WeixinUserInfo.class));
		}
	}

	/**
	 * 创建用户分组
	 * 
	 * @param credential
	 * @param groupName
	 * @return
	 */
	public MpResponseData<WeixinUserGroup> createGroup(MpDevCredential credential, String groupName) {
		isTrue(credential != null && credential.isWellformed());
		hasLength(groupName);

		WeixinUserGroup group = new WeixinUserGroup(groupName);
		String result = getRestTemplate().postForObject(
				MessageFormat.format(MpConstant.GROUP_CREATE_URL, getAccessToken(credential)),
				MessageMapper.toJson(group, SerializationFeature.WRAP_ROOT_VALUE), String.class);
		if (StringUtils.containsIgnoreCase(result, "errcode")) {
			MpResponse response = MessageMapper.fromJson(result, MpResponse.class);
			logger.error(MessageFormat.format("创建用户分组失败，错误码：{0}，错误提示：{1}", response.getErrCode(), response.getErrMsg()));
			return new MpResponseData<WeixinUserGroup>(response);
		} else {
			return new MpResponseData<WeixinUserGroup>(MessageMapper.fromJson(result, WeixinUserGroup.class,
					DeserializationFeature.UNWRAP_ROOT_VALUE));
		}
	}

	private final static String _GROUP_FETCH_KEY = "groups";

	/**
	 * 查询所有分组
	 * 
	 * @param credential
	 * @return
	 */
	public MpResponseData<List<WeixinUserGroup>> getGroups(MpDevCredential credential) {
		isTrue(credential != null && credential.isWellformed());

		String result = getRestTemplate().getForObject(
				MessageFormat.format(MpConstant.GROUP_FETCH_URL, getAccessToken(credential)), String.class);
		if (StringUtils.containsIgnoreCase(result, "errcode")) {
			MpResponse response = MessageMapper.fromJson(result, MpResponse.class);
			logger.error(MessageFormat.format("获取分组信息失败，错误码：{0}，错误提示：{1}", response.getErrCode(), response.getErrMsg()));
			return new MpResponseData<List<WeixinUserGroup>>(response);
		} else {
			Map<String, List<WeixinUserGroup>> map = MessageMapper.fromJson(
					result,
					MessageMapper.getTypeFactory().constructMapType(HashMap.class,
							MessageMapper.getTypeFactory().constructType(String.class),
							MessageMapper.createCollectionType(ArrayList.class, WeixinUserGroup.class)));
			if (map != null && map.containsKey(_GROUP_FETCH_KEY)) {
				return new MpResponseData<List<WeixinUserGroup>>(map.get(_GROUP_FETCH_KEY));
			} else {
				List<WeixinUserGroup> groups = Collections.emptyList();
				return new MpResponseData<List<WeixinUserGroup>>(groups);
			}
		}
	}

	/**
	 * 查询用户所在分组
	 * 
	 * @param credential
	 * @param openId
	 * @return
	 */
	public MpResponseData<String> getUserGroup(MpDevCredential credential, String openId) {
		isTrue(credential != null && credential.isWellformed());
		hasLength(openId);

		Map<String, String> requst = Maps.newHashMap();
		requst.put("openid", openId);

		String result = getRestTemplate().postForObject(
				MessageFormat.format(MpConstant.USER_GROUP_QUERY_URL, getAccessToken(credential)),
				MessageMapper.toJson(requst), String.class);

		if (StringUtils.containsIgnoreCase(result, "errcode")) {
			MpResponse response = MessageMapper.fromJson(result, MpResponse.class);
			logger.error(MessageFormat.format("获取用户所在分组失败，错误码：{0}，错误提示：{1}", response.getErrCode(),
					response.getErrMsg()));
			return new MpResponseData<>(response);
		} else {
			Map<String, String> map = MessageMapper.fromJson(result,
					MessageMapper.createCollectionType(HashMap.class, String.class, String.class));
			if (map != null && map.containsKey("groupid")) {
				return new MpResponseData<String>(map.get("groupid"));
			} else {
				return new MpResponseData<String>("");
			}
		}
	}

	/**
	 * 修改分组名
	 * 
	 * @param credential
	 * @param group
	 * @return
	 */
	public MpResponse updateGroup(MpDevCredential credential, WeixinUserGroup group) {
		isTrue(credential != null && credential.isWellformed());
		notNull(group);

		return getRestTemplate().postForObject(
				MessageFormat.format(MpConstant.GROUP_UPDATE_URL, getAccessToken(credential)),
				MessageMapper.toJson(group, SerializationFeature.WRAP_ROOT_VALUE), MpResponse.class);
	}

	/**
	 * 移动用户分组
	 * 
	 * @param credential
	 * @param openId
	 * @param newGroupId
	 * @return
	 */
	public MpResponse updateUserGroup(MpDevCredential credential, String openId, int newGroupId) {
		isTrue(credential != null && credential.isWellformed());
		hasLength(openId);

		Map<String, Object> request = Maps.newHashMap();
		request.put("openid", openId);
		request.put("to_groupid", newGroupId);

		return getRestTemplate().postForObject(
				MessageFormat.format(MpConstant.USER_GROUP_UPDATE_URL, getAccessToken(credential)),
				MessageMapper.toJson(request), MpResponse.class);
	}

	/**
	 * 设置用户备注名
	 * 
	 * @param credential
	 * @param openId
	 * @param remark
	 * @return
	 */
	public MpResponse updateUserRemark(MpDevCredential credential, String openId, String remark) {
		isTrue(credential != null && credential.isWellformed());
		hasLength(openId);
		hasLength(remark);

		Map<String, Object> request = Maps.newHashMap();
		request.put("openid", openId);
		request.put("remark", remark);

		return getRestTemplate().postForObject(
				MessageFormat.format(MpConstant.USER_REMARK_UPDATE_URL, getAccessToken(credential)),
				MessageMapper.toJson(request), MpResponse.class);
	}
}
