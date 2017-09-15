package com.sidc.sits.logical.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmResponseBean;
import com.sidc.blackcore.api.sits.hotelinfo.response.HotelInfoResponse;
import com.sidc.configuration.blackcore.AppFcmKey;
import com.sidc.configuration.blackcore.AppFcmKeyConfiguration;
import com.sidc.configuration.conf.Env;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.encrypt.AesEncrypt;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class FcmUtils {
	/**
	 * Send FCM message from http post
	 * 
	 * @param entity
	 * @throws SiDCException
	 */
	public void sendFcmProcess(final FcmBean entity) throws SiDCException {
		final String hotelCode = getHotelInfo();

		if (StringUtils.isBlank(hotelCode)) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find hotel info.");
		}

		final AppFcmKeyConfiguration appFcmKeyConfiguration = (AppFcmKeyConfiguration) DataCenter.getInstance()
				.get(Env.APP_FCM_KEY_CONFIGURATION);

		String fcmAuthKey = null;

		for (final AppFcmKey fcmKeyEntity : appFcmKeyConfiguration.getKeys()) {
			if (fcmKeyEntity.getName().equals(hotelCode)) {
				fcmAuthKey = fcmKeyEntity.getKey();
			}
		}

		if (StringUtils.isBlank(fcmAuthKey)) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find fcm key,hotel code:" + hotelCode + ".");
		}

		try {
			final String response = HttpClientUtils.sendPostWithFcm(entity, fcmAuthKey);

			final Gson gson = new Gson();
			final FcmResponseBean fcmResponseBean = (FcmResponseBean) gson.fromJson(response, FcmResponseBean.class);
			// FCM回傳回來 要等於 1 才代表成功
			if (!fcmResponseBean.getSuccess().equals("1")) {
				throw new SiDCException(APIStatus.FCM_ERROR, fcmResponseBean.getResults().get(0).getError());
			}

		} catch (SiDCException e) {
			throw new SiDCException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new SiDCException(APIStatus.HTTP_METHOD_FAIL, "send fcm fail");
		}
	}

	/**
	 * 從 sits取得 hotel 資訊 ,http post 方式 ,sits版本需要 5.32
	 * 
	 * @return
	 * @throws SiDCException
	 */
	private String getHotelInfo() throws SiDCException {
		HotelInfoResponse hotelEntity = (HotelInfoResponse) DataCenter.getInstance().get(Env.HOTEL_INFO);
		if (hotelEntity == null || StringUtils.isBlank(hotelEntity.getHotelcode())) {
			final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

			try {
				final AesEncrypt encrypt = new AesEncrypt("sidc-sits");
				hotelEntity = HttpClientUtils.sendPostWithHotelInfo(encrypt.encrypt(formatter.format(new Date())));
				DataCenter.getInstance().put(Env.HOTEL_INFO, hotelEntity);

			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				throw new SiDCException(APIStatus.ENCRYPT_FAIL, "AES encrypt fail.");
			} catch (SiDCException e) {
				throw new SiDCException(e.getErrorCode(), e.getMessage());
			} catch (Exception e) {
				throw new SiDCException(APIStatus.HTTP_METHOD_FAIL, "send post fail.");
			}
		}
		return hotelEntity.getHotelcode();
	}

}
