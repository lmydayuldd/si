package com.sidc.sits.logical.activity;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.google.gson.Gson;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeInsertBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityLangBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivityUpdateRequest;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.ActivityManager;
import com.sidc.dao.sits.manager.PhotoManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ActivityUpdateProcess extends AbstractAuthAPIProcess {
	final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private final ActivityUpdateRequest entity;
	private final String STEP = "3";

	public ActivityUpdateProcess(final ActivityUpdateRequest entity) {
		// TODO Auto-generated constructor stub
		super(entity.getToken());
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// 先記錄要刪的資料
		final List<String> photoNameList = PhotoManager.getInstance().seleceNameById(String.valueOf(entity.getId()),
				PhotoType.ACTIVITY.toString());

		if (entity.getRepeattype().equals("3")) {
			// 每周重複的資料,限定當天
			for (ActivityRepeatBean repeatEntity : entity.getRepeatlist()) {
				repeatEntity.setStarttime("0000/00/00" + repeatEntity.getStarttime().substring(10));
				repeatEntity.setEndtime("0000/00/00" + repeatEntity.getEndtime().substring(10));
			}
		}
		LogAction.getInstance().debug("step 1/" + STEP + " :format repeat date success.");

		ActivityManager.getInstance().update(entity.getId(), entity.getType(), entity.getRepeattype(),
				entity.getLowerlimit(), entity.getUpperlimit(), 0, entity.getTarget(),
				entity.getRegistrationstarttime(), entity.getRegistrationendtime(), entity.getIscharge(),
				entity.getMemo(), entity.getList(), entity.getFeelist(), entity.getRepeatlist(), entity.getPhotolist(),
				entity.isPhotoupdate(), new Gson().toJson(entity.getRepeatfrequent()));
		LogAction.getInstance().debug("step 2/" + STEP + " :update success(ActivityManager|update).");

		List<PhotoUploadBean> photoList = new ArrayList<PhotoUploadBean>();
		for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
			photoList.add(new PhotoUploadBean(photoEntity.getPhoto(),
					photoEntity.getName() + "." + photoEntity.getExtension()));
		}

		if (entity.isPhotoupdate() && !photoList.isEmpty()) {
			LogAction.getInstance().debug("has photo need to upload.");

			final List<String> folderList = Arrays.asList(String.valueOf(entity.getId()));
			final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());

			// 通知 sits 上傳圖片
			try {
				HttpClientUtils.sendPostWithUploadPhoto(sitsUrl + PageList.UPLOAD_PHOTO, folderList,
						PhotoType.ACTIVITY.toString(), photoList);
				LogAction.getInstance().debug("upload success.");
			} catch (Exception e) {
				LogAction.getInstance().error("photo upload fail !!", e);

				for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
					PhotoManager.getInstance().updateWithBackup(String.valueOf(entity.getId()), "2",
							photoEntity.getPhoto());
				}
				LogAction.getInstance().warn("file insert to database success.");
			}

			// 通知 sits 把舊圖刪了
			try {
				HttpClientUtils.sendPostWithDeletePhoto(sitsUrl + PageList.DELETE_PHOTO, folderList,
						PhotoType.ACTIVITY.toString(), photoNameList);
				LogAction.getInstance().debug("delete success.");
			} catch (Exception e) {
				LogAction.getInstance().error("photo delete fail !!", e);
			}
		}
		LogAction.getInstance().debug("step 3/" + STEP + " :photo process success.");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final Date date = new Date();
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(token).");
		}
		if (entity.getId() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(id).");
		}
		if (!StringUtils.isBlank(entity.getRegistrationstarttime())
				&& !StringUtils.isBlank(entity.getRegistrationendtime())) {
			if (entity.getRegistrationstarttime().length() != 16) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration start time not datetime format yyyy/MM/dd HH:mm).");
			}
			if (entity.getRegistrationendtime().length() != 16) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration end time not datetime format yyyy/MM/dd HH:mm).");
			}
			if (!isDate(entity.getRegistrationstarttime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration start time not datetime format yyyy/MM/dd HH:mm).");
			}
			if (!isDate(entity.getRegistrationendtime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration end time not datetime format yyyy/MM/dd HH:mm).");
			}
			if (!checkSequence(entity.getRegistrationstarttime(), entity.getRegistrationendtime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration time sequence error).");
			}
			if (!checkSequence(formatter.format(date), entity.getRegistrationstarttime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration start time is less than the system time.");
			}
			if (!checkSequence(formatter.format(date), entity.getRegistrationendtime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration end time is less than the system time.");
			}
		}
		if (StringUtils.isBlank(entity.getRepeattype())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat type).");
		}
		if (StringUtils.isBlank(entity.getMemo()) && entity.getMemo().length() > 1024) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(memo length).");
		}
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(list).");
		}
		for (final ActivityLangBean activityLangEntity : entity.getList()) {
			if (StringUtils.isBlank(activityLangEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langecode).");
			}
			if (StringUtils.isBlank(activityLangEntity.getContent())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(content).");
			}
			if (StringUtils.isBlank(activityLangEntity.getLocation())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(location).");
			}
			if (StringUtils.isBlank(activityLangEntity.getTitle())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(title).");
			}
			if (activityLangEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langcode length).");
			}
			if (activityLangEntity.getContent().length() > 2048) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(content length).");
			}
			if (activityLangEntity.getLocation().length() > 20) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(content length).");
			}
			if (activityLangEntity.getTitle().length() > 30) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(title length).");
			}
		}
		if (entity.getIscharge() == 1) {
			if (entity.getFeelist() == null || entity.getFeelist().isEmpty()) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(fee list).");
			}
		}
		for (final ActivityFeeInsertBean activityFeeEntity : entity.getFeelist()) {
			if (activityFeeEntity.getFeeid() < 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(fee id).");
			}
			if (StringUtils.isBlank(activityFeeEntity.getPrice())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(price).");
			}
			if (activityFeeEntity.getPrice().length() > 10) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(price length).");
			}
			if (!ActivityManager.getInstance().isExistOfFeeID(activityFeeEntity.getFeeid())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "fee id is not existed.");
			}
		}
		if (entity.getRepeatlist() == null || entity.getRepeatlist().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat list).");
		}
		for (final ActivityRepeatBean activityRepeatEntity : entity.getRepeatlist()) {
			if (StringUtils.isBlank(activityRepeatEntity.getStarttime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(start time).");
			}
			if (!isDate(activityRepeatEntity.getStarttime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(start time not datetime format yyyy/MM/dd HH:mm).");
			}
			if (!checkSequence(formatter.format(date), activityRepeatEntity.getStarttime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(start time is less than the system time.");
			}
			if (!StringUtils.isBlank(activityRepeatEntity.getDescription())) {
				if (activityRepeatEntity.getDescription().length() > 1024) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat description).");
				}
			}
			if (!StringUtils.isBlank(activityRepeatEntity.getEndtime())) {
				if (!isDate(activityRepeatEntity.getEndtime())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
							"illegal of request(end time not datetime format yyyy/MM/dd HH:mm).");
				}
				if (!checkSequence(formatter.format(date), activityRepeatEntity.getEndtime())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
							"illegal of request(end time is less than the system time.");
				}
				if (!checkSequence(activityRepeatEntity.getStarttime(), activityRepeatEntity.getEndtime())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
							"illegal of request(repeat time sequence error).");
				}
			}
		}
		if (entity.getRepeattype().equals("3")) {
			if (entity.getRepeatfrequent() == null) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat frequent).");
			}
			if (ArrayUtils.isEmpty(entity.getRepeatfrequent().getDays())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat frequent days).");
			}
			final String days[] = { "1", "2", "3", "4", "5", "6", "7" };

			for (String day : entity.getRepeatfrequent().getDays()) {
				if (!ArrayUtils.contains(days, day)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat frequent day).");
				}
			}
		} else if (entity.getRepeattype().equals("1")) {
			// 單一場次 只放一筆資料
			List<ActivityRepeatBean> repeatList = new ArrayList<ActivityRepeatBean>();
			repeatList.add(entity.getRepeatlist().get(0));
			entity.setRepeatlist(repeatList);
			entity.setRepeatfrequent(null);
		} else {// 不是 有重複 要清除掉，才不會insert to db
			entity.setRepeatfrequent(null);
		}
		if (!entity.isPhotoupdate()) {
			entity.setPhotolist(null);
		}
		if (entity.getPhotolist() == null) {
			entity.setPhotolist(new ArrayList<ActivityPhotoUploadBean>());
		} else {
			final int max = Integer.valueOf(
					SystemPropertiesManager.getInstance().findPropertiesMessage("upload.image.capacity.maximum"));

			for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
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
		if (!ActivityManager.getInstance().isExistOfActivityID(entity.getId())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "id is not existed.");
		}
		if (!ActivityManager.getInstance().isExistOfTypeID(entity.getType())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "type id is not existed.");
		}
	}

	/***
	 * 檢查是否為時間格式
	 * 
	 * @param dttm
	 * @return
	 */
	private boolean isDate(final String dttm) {
		formatter.setLenient(false);
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(dttm, pos);

		if (date == null || pos.getErrorIndex() > 0) {
			return false;
		}
		if (pos.getIndex() != dttm.length()) {
			return false;
		}
		if (formatter.getCalendar().get(Calendar.YEAR) > 9999) {
			return false;
		}
		return true;
	}

	/***
	 * 檢查 起始時間 < 結束時間
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private boolean checkSequence(final String startTime, final String endTime) {
		formatter.setLenient(false);
		Date startDate = formatter.parse(startTime, new ParsePosition(0));
		Date endDate = formatter.parse(endTime, new ParsePosition(0));

		if (startDate.getTime() > endDate.getTime()) {
			return false;
		}
		return true;
	}
}
