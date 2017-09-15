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
import com.sidc.blackcore.api.mobile.message.bean.FcmNewslettersBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmNewslettersInfoBean;
import com.sidc.blackcore.api.mobile.message.bean.FcmResultsBean;
import com.sidc.blackcore.api.mobile.message.request.NewslettersRequest;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.configuration.blackcore.AppFcmKeyConfiguration;
import com.sidc.configuration.conf.Env;
import com.sidc.configuration.conf.SidcUrlName;
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
public class NewslettersProcess extends AbstractAuthAPIProcess {
	private final NewslettersRequest enity;
	private final String STEP = "6";
	private String fcmAuthKey;
	private String fcmUrl;
	private final Gson gson = new Gson();

	public NewslettersProcess(final NewslettersRequest enity) {
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

		fcmAuthKey = appFcmKeyConfiguration.getKeys().get(1).getKey();
		fcmUrl = UrlUtils.getUrl(SidcUrlName.FCM.toString());

		LogAction.getInstance().debug("Fcm auth key=" + fcmAuthKey + " Url=" + fcmUrl);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final Date date = new Date();
		FcmNewslettersBean fcmEntity = null;
		List<String> registrationList = new ArrayList<String>();
		List<String> mobileIdList = new ArrayList<String>();

		switch (enity.getType()) {
		case "room":
			registrationList = MessageManager.getInstance().getMobilePushToken(enity.getData().getRoomno());
			mobileIdList = MessageManager.getInstance().getMobileInfoId(enity.getData().getRoomno());
			fcmEntity = new FcmNewslettersBean(registrationList);
			break;
		case "device":
			registrationList.add(enity.getData().getPushtoken());
			mobileIdList.add(enity.getData().getPushtoken());
			enity.getData().setRoomno(null);// 針對 device 移除 room no 避免 誤會
			fcmEntity = new FcmNewslettersBean(registrationList);
			break;
		case "group":
			registrationList = MessageManager.getInstance().getMobilePushTokenByGroupId(enity.getData().getGroupid());
			mobileIdList = MessageManager.getInstance().getMobileInfoIdByGroupId(enity.getData().getGroupid());
			fcmEntity = new FcmNewslettersBean("/topics/group" + enity.getData().getGroupid());
			break;
		case "allcheckin":
			registrationList = MessageManager.getInstance().getMobilePushTokenWithAllCheckIn();
			mobileIdList = MessageManager.getInstance().getMobileInfoIdWithCheckin();
			fcmEntity = new FcmNewslettersBean("/topics/group/allcheckin");
			break;
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :get registration list success.");

		if (registrationList.isEmpty()) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "fcm push token is empty.");
		}

		if (mobileIdList.isEmpty()) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "No information can be sent fcm messge.");
		}

		// 新增資料 進DB
		final List<String> messageIdList = MessageManager.getInstance().insertNewlettersMessage(
				enity.getData().getRoomno(), enity.getStaffid(), mobileIdList, enity.getData().getTitle(),
				enity.getData().getMessage(), enity.getData().getPhotolist());
		LogAction.getInstance()
				.debug("step 3/" + STEP + ":insert message(MessageManager|insertNewlettersMessage)success.");

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

		LogAction.getInstance().debug("step 4/" + STEP + ":photo url=" + photoUrl);

		List<ActivityPhotoBean> imagelist = new ArrayList<ActivityPhotoBean>();
		if (!photoList.isEmpty()) {
			// fcm要用的資料 要加上 完整路徑

			for (final ActivityPhotoUploadBean photoEntity : enity.getData().getPhotolist()) {
				imagelist.add(new ActivityPhotoBean(photoEntity.getName() + "." + photoEntity.getExtension()));
			}
			for (final String id : messageIdList) {
				List<String> folderList = Arrays.asList(id);

				// 通知sits上傳圖片
				ImageUtils.sendPhotoToSits(id, folderList, photoList, PhotoType.NEWSLETTERS.toString());
			}
		}
		FcmNewslettersInfoBean infoEntity = new FcmNewslettersInfoBean(messageIdList.get(0), enity.getData().getTitle(),
				enity.getData().getMessage(), formatter.format(date));
		fcmEntity.setData(infoEntity);
		fcmProcess(fcmEntity, messageIdList);

		/*
		 * List<FcmNewslettersMessageBean> messageList = new
		 * ArrayList<FcmNewslettersMessageBean>();
		 * 
		 * if (!photoList.isEmpty()) { for (final String id : messageIdList) {
		 * List<String> folderList = Arrays.asList(id);
		 * 
		 * // 通知sits上傳圖片 ImageUtils.sendPhotoToSits(id, folderList, photoList,
		 * PhotoType.NEWSLETTERS.toString());
		 * 
		 * // fcm要用的資料 要加上 完整路徑 List<ActivityPhotoBean> imagelist = new
		 * ArrayList<ActivityPhotoBean>(); for (final ActivityPhotoUploadBean
		 * photoEntity : enity.getData().getPhotolist()) { imagelist.add(new
		 * ActivityPhotoBean( photoUrl + id + "/" + photoEntity.getName() + "."
		 * + photoEntity.getExtension())); } messageList.add(new
		 * FcmNewslettersMessageBean(id, enity.getData().getMessage(),
		 * formatter.format(date), imagelist)); } }
		 * LogAction.getInstance().debug("step 5/" + STEP +
		 * ":send to sits upload photo success.");
		 * 
		 * // Fcm max notification size 是 2048bytes final int size =
		 * gson.toJson(messageList).getBytes().length; if (size > 1900) {// 超過限制
		 * 分段發送 List<FcmNewslettersMessageBean> messagePartList = new
		 * ArrayList<FcmNewslettersMessageBean>();
		 * 
		 * for (int x = 0; x < messageList.size(); x++) { final
		 * FcmNewslettersMessageBean messageEntity = messageList.get(x); if
		 * (gson.toJson(messagePartList).getBytes().length +
		 * gson.toJson(messageEntity).getBytes().length > 1900) {
		 * 
		 * fcmEntity.setData(new FcmNewslettersObjectBean(messagePartList));
		 * fcmProcess(fcmEntity, messageIdList);
		 * 
		 * messagePartList = new ArrayList<FcmNewslettersMessageBean>();
		 * messagePartList.add(messageEntity); } else {
		 * messagePartList.add(messageEntity); } } // fcm process
		 * fcmEntity.setData(new FcmNewslettersObjectBean(messagePartList));
		 * fcmProcess(fcmEntity, messageIdList); } else { // fcm process
		 * fcmEntity.setData(new FcmNewslettersObjectBean(messageList));
		 * fcmProcess(fcmEntity, messageIdList); }
		 */
		LogAction.getInstance().debug("step 6/" + STEP + ":send to fcm success.");
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
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
		if (StringUtils.isBlank(enity.getData().getMessage())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of message.");
		}
		if (StringUtils.isBlank(enity.getData().getTitle())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of title.");
		}
		if (StringUtils.isBlank(fcmAuthKey)) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "fcm auth key is empty.");
		}
		if (StringUtils.isBlank(fcmUrl)) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "fcm url is empty.");
		}
		if (!StaffManager.getInstance().isExist(enity.getToken(), enity.getStaffid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "token and staff id is not match.");
		}
	}

	private void fcmProcess(final FcmNewslettersBean entity, final List<String> idList) throws Exception {

		final String json = gson.toJson(entity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Authorization", "key=" + fcmAuthKey);
		final String response = HttpClientUtils.httpSendPost(fcmUrl, strEntity, map);

		try {
			final FcmResultsBean fcmResponseBean = (FcmResultsBean) gson.fromJson(response, FcmResultsBean.class);
			if (StringUtils.isBlank(fcmResponseBean.getMessage_id())) {
				throw new SiDCException(APIStatus.FCM_ERROR, response);
			}
			// FCM回傳回來 要等於 1 才代表成功
			// if (!fcmResponseBean.getSuccess().equals("1")) {
			// MessageManager.getInstance().updateStatus(idList, -10);
			// throw new SiDCException(APIStatus.FCM_ERROR,
			// fcmResponseBean.getResults().get(0).getError());
			// }
		} catch (Exception e) {
			MessageManager.getInstance().updateStatus(idList, -10);
			throw new SiDCException(APIStatus.FCM_ERROR, response);
		}
	}
}
