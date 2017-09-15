package com.sidc.sits.logical.message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.google.gson.Gson;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmMessageBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmResponseBean;
import com.sidc.blackcore.api.mobile.message.request.HotelMessageRequest;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.configuration.blackcore.AppFcmKeyConfiguration;
import com.sidc.configuration.conf.Env;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.AccountManager;
import com.sidc.dao.sits.manager.MessageManager;
import com.sidc.dao.sits.manager.StaffManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.ImageUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class HotelMessageProcess extends AbstractAuthAPIProcess {

	private final HotelMessageRequest enity;
	private final String STEP = "4";
	private String fcmAuthKey;
	private String fcmUrl;
	private final Gson gson = new Gson();

	public HotelMessageProcess(final HotelMessageRequest enity) {
		super(enity.getToken());
		this.enity = enity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + enity);

		// 未來請用 hotel name 照到相對的...
		AppFcmKeyConfiguration appFcmKeyConfiguration = (AppFcmKeyConfiguration) DataCenter.getInstance()
				.get(Env.APP_FCM_KEY_CONFIGURATION);

		fcmAuthKey = appFcmKeyConfiguration.getKeys().get(0).getKey();
		fcmUrl = UrlUtils.getUrl(SidcUrlName.FCM.toString());

		LogAction.getInstance().debug("Fcm auth key=" + fcmAuthKey + " Url=" + fcmUrl);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		final String staffName = StaffManager.getInstance().getStaffNameById(enity.getStaffid());
		LogAction.getInstance().debug("step 1/" + STEP + " :get staff name success.");

		if (StringUtils.isBlank(staffName)) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find staff name.");
		}

		final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final Date date = new Date();
		FcmBean fcmEntity = null;
		List<String> registrationList = new ArrayList<String>();
		List<String> mobileIdList = new ArrayList<String>();

		switch (enity.getType()) {
		case "room":
			registrationList = MessageManager.getInstance().getMobilePushToken(enity.getData().getRoomno());
			mobileIdList = MessageManager.getInstance().getMobileInfoId(enity.getData().getRoomno());
			fcmEntity = new FcmBean(registrationList);
			break;
		case "device":
			registrationList.add(enity.getData().getPushtoken());
			mobileIdList.add(enity.getData().getPushtoken());
			enity.getData().setRoomno(null);// 針對 device 移除 room no 避免 誤會
			fcmEntity = new FcmBean(registrationList);
			break;
		case "group":
			registrationList = MessageManager.getInstance().getMobilePushTokenByGroupId(enity.getData().getGroupid());
			mobileIdList = MessageManager.getInstance().getMobileInfoIdByGroupId(enity.getData().getGroupid());
			fcmEntity = new FcmBean("/topics/group/" + enity.getData().getGroupid());
			break;
		case "allcheckin":
			registrationList = MessageManager.getInstance().getMobilePushTokenWithAllCheckIn();
			mobileIdList = MessageManager.getInstance().getMobileInfoIdWithCheckin();
			fcmEntity = new FcmBean("/topics/group/allcheckin");
			break;
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :get registration list success.");

		if (registrationList.isEmpty()) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "fcm push token is empty.");
		}
		// 新增資料進DB
		final List<String> messageIdList = MessageManager.getInstance().insertChatMessageWithHotel(
				enity.getData().getRoomno(), enity.getStaffid(), mobileIdList, enity.getData().getMessage());
		LogAction.getInstance().debug("step 3/" + STEP + " :insert message(MessageManager|insertChatMessage) success.");

		// photo process
		List<PhotoUploadBean> photoList = new ArrayList<PhotoUploadBean>();

		for (final ActivityPhotoUploadBean photoEntity : enity.getData().getPhotolist()) {
			photoList.add(new PhotoUploadBean(photoEntity.getPhoto(),
					photoEntity.getName() + "." + photoEntity.getExtension()));
		}

		// 取參數
		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		final String photoUrl = imageUrl
				+ SystemPropertiesManager.getInstance().findPropertiesMessage("upload.newsletters.path");

		List<FcmMessageBean> messageList = new ArrayList<FcmMessageBean>();

		if (!photoList.isEmpty()) {
			for (final String id : messageIdList) {
				List<String> folderList = Arrays.asList(id);
				// 通知sits上傳圖片
				ImageUtils.sendPhotoToSits(id, folderList, photoList, PhotoType.NEWSLETTERS.toString());
				// fcm要用的資料 要加上 完整路徑
				List<ActivityPhotoBean> imagelist = new ArrayList<ActivityPhotoBean>();
				for (final ActivityPhotoUploadBean photoEntity : enity.getData().getPhotolist()) {
					imagelist.add(new ActivityPhotoBean(
							photoUrl + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension()));
				}
				messageList.add(new FcmMessageBean(id, enity.getData().getMessage(), enity.getData().getRoomno(),
						staffName, enity.getStaffid(), "0", formatter.format(date), imagelist));
			}
		}

		// 實作 FCM
		final FcmMessageBean messageBean = new FcmMessageBean(null, enity.getData().getMessage(),
				enity.getData().getRoomno(), staffName, enity.getStaffid(), "0", new Date().toString());

		final FcmBean enity = new FcmBean(registrationList, messageBean);
		fcmProcess(enity, messageIdList);

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(enity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of token.");
		}
		if (StringUtils.isBlank(enity.getStaffid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of staff id.");
		}
		if (StringUtils.isBlank(enity.getType())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of type.");
		}
		if (enity.getData() == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of data.");
		}
		if (enity.getType().equals("room")) {
			if (StringUtils.isBlank(enity.getData().getRoomno())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
			}
		} else if (enity.getType().equals("device")) {
			if (StringUtils.isBlank(enity.getData().getPushtoken())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of push token.");
			}
		} else if (enity.getType().equals("group")) {
			if (StringUtils.isBlank(enity.getData().getGroupid())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of group id.");
			}
		} else if (enity.getType().equals("allcheckin")) {

		} else {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of type.");
		}
		if (StringUtils.isBlank(enity.getData().getMessage())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of message.");
		}
		if (enity.getData().getPhotolist() == null) {
			enity.getData().setPhotolist(new ArrayList<ActivityPhotoUploadBean>());
		} else {
			final int max = Integer.valueOf(
					SystemPropertiesManager.getInstance().findPropertiesMessage("upload.image.capacity.maximum"));

			for (final ActivityPhotoUploadBean photoEntity : enity.getData().getPhotolist()) {
				final int photoLength = photoEntity.getPhoto().length;

				if (photoEntity.getPhoto() == null || photoLength <= 0) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo).");
				}
				if (photoLength / 1024 > max) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo over limit size).");
				}
				if (StringUtils.isBlank(photoEntity.getName())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo name).");
				}
				if (StringUtils.isBlank(photoEntity.getExtension())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo extension).");
				}
			}
		}
		if (StringUtils.isBlank(fcmAuthKey)) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "fcm auth key is empty.");
		}
		if (StringUtils.isBlank(fcmUrl)) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "fcm url is empty.");
		}
		if (!AccountManager.getInstance().checkId(enity.getStaffid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find staff id.");
		}
	}

	private void fcmProcess(final FcmBean entity, final List<String> idList) throws Exception {

		final String json = gson.toJson(entity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Authorization", "key=" + fcmAuthKey);
		final String response = HttpClientUtils.httpSendPost(fcmUrl, strEntity, map);

		try {
			final FcmResponseBean fcmResponseBean = (FcmResponseBean) gson.fromJson(response, FcmResponseBean.class);
			// FCM回傳回來 要等於 1 才代表成功
			if (!fcmResponseBean.getSuccess().equals("1")) {
				MessageManager.getInstance().updateStatus(idList, -10);
				throw new SiDCException(APIStatus.FCM_ERROR, fcmResponseBean.getResults().get(0).getError());
			}
		} catch (Exception e) {
			MessageManager.getInstance().updateStatus(idList, -10);
			throw new SiDCException(APIStatus.FCM_ERROR, response);
		}
	}
}
