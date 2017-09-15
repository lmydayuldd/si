package com.sidc.sits.logical.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmMessageBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmResponseBean;
import com.sidc.blackcore.api.mobile.message.request.GuestMessageRequest;
import com.sidc.blackcore.api.mobile.message.response.GuestMessageResponse;
import com.sidc.configuration.blackcore.AppFcmKeyConfiguration;
import com.sidc.configuration.conf.Env;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.MessageManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.encrypt.AesEncrypt;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class GuestMessageProcess extends AbstractAuthAPIProcess {

	private final GuestMessageRequest entity;
	private final String STEP = "2";
	private String fcmAuthKey;
	private String fcmUrl;

	public GuestMessageProcess(final GuestMessageRequest entity) {
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno());
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);

		// 未來請用 hotel name 找到相對的...
		AppFcmKeyConfiguration appFcmKeyConfiguration = (AppFcmKeyConfiguration) DataCenter.getInstance()
				.get(Env.APP_FCM_KEY_CONFIGURATION);

		fcmAuthKey = appFcmKeyConfiguration.getKeys().get(0).getKey();
		fcmUrl = UrlUtils.getUrl(SidcUrlName.FCM.toString());

		LogAction.getInstance().debug("Fcm auth key=" + fcmAuthKey + " Url=" + fcmUrl);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		AesEncrypt encrypt = new AesEncrypt("sidc");
		// final String publicKey = encrypt.decrypt(entity.getPublickey());
		// final String privateKey = encrypt.decrypt(entity.getPrivatekey());

		// 等 yayo大哥 解了 aes再開
		final String publicKey = entity.getPublickey();
		final String privateKey = entity.getPrivatekey();

		final String messageId = MessageManager.getInstance().insertChatMessageWithGuest(publicKey, privateKey,
				entity.getRoomno(), entity.getGuestname(), entity.getMessage());
		LogAction.getInstance().debug("step 1/" + STEP + ":insert success(MessageManager|insertChatMessageWithGuest).");

		// 待討論 要綁user or device
		List<String> registrationList = new ArrayList<String>();

		final FcmMessageBean messageBean = new FcmMessageBean(null, entity.getMessage(), entity.getRoomno(),
				entity.getGuestname(), "", "1", new Date().toString());

		final FcmBean enity = new FcmBean(registrationList, messageBean);

		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Authorization", "key=" + fcmAuthKey);
		final String response = HttpClientUtils.httpSendPost(fcmUrl, strEntity, map);

		try {
			final FcmResponseBean fcmResponseBean = (FcmResponseBean) gson.fromJson(response, FcmResponseBean.class);
			LogAction.getInstance().debug("step 2/" + STEP + " :send message success.");

			// FCM回傳回來 要等於 1 才代表成功
			if (!fcmResponseBean.getSuccess().equals("1")) {
				throw new SiDCException(APIStatus.FCM_ERROR, fcmResponseBean.getResults().get(0).getError());
			}
		} catch (Exception e) {
			throw new SiDCException(APIStatus.FCM_ERROR, response);
		}

		return new GuestMessageResponse(messageId);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of private key.");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of public key.");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
		}
		if (StringUtils.isBlank(entity.getGuestname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of guest name.");
		}
		if (StringUtils.isBlank(entity.getMessage())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of message.");
		}
	}
}
