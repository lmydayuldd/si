package com.sidc.sits.logical.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatFrequentBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivityRequest;
import com.sidc.blackcore.api.mobile.activity.response.ActivityResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.ActivityManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ActivityProcess extends AbstractAPIProcess {
	private final ActivityRequest entity;
	private final String STEP = "1";

	public ActivityProcess(final ActivityRequest entity) {
		// TODO Auto-generated constructor stub
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		List<ActivityBean> list = new ArrayList<ActivityBean>();

		int status = -1;
		String repeatType = null;
		int target = -1;
		int charge = -1;
		String langCode = null;

		switch (entity.getStatus()) {
		case "0":
			status = 0;
			break;
		case "1":
			status = 1;
			break;
		case "2":
			status = 2;
			break;
		}

		if (!entity.getRepeattype().equals("all")) {
			repeatType = entity.getRepeattype();
		}

		if (!entity.getLangcode().equals("all")) {
			langCode = entity.getLangcode();
		}

		switch (entity.getTarget()) {
		case "0":
			target = 0;
			break;
		case "1":
			target = 1;
			break;
		}

		switch (entity.getCharge()) {
		case "0":
			charge = 0;
			break;
		case "1":
			charge = 1;
			break;
		}

		list = ActivityManager.getInstance().selectActivity(langCode, entity.getId(), entity.getType(), repeatType,
				status, target, charge);
		LogAction.getInstance().debug("step 1/" + STEP + " :select success.");

		// 取參數
		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		String photoUrl = SystemPropertiesManager.getInstance().findPropertiesMessage("upload.activity.path");
		photoUrl = photoUrl.substring(0, photoUrl.length() - 1);

		List<ActivityResponse> responseList = new ArrayList<ActivityResponse>();

		final Gson gson = new Gson();

		for (ActivityBean activityEntity : list) {
			List<String> repeatfrequentlist = new ArrayList<String>();
			for (ActivityPhotoBean photoEntity : activityEntity.getPhotolist()) {
				final String url = photoEntity.getPhoto();
				photoEntity.setPhoto(imageUrl + photoUrl + url);
			}

			if (!StringUtils.isBlank(activityEntity.getRepeatfrequent())) {
				repeatfrequentlist = repeatFrequencyPrcoess(activityEntity.getRepeatfrequent(), gson);
			}

			responseList.add(new ActivityResponse(activityEntity.getId(), activityEntity.getType(),
					activityEntity.getStatus(), activityEntity.getRepeattype(), activityEntity.getLowerlimit(),
					activityEntity.getUpperlimit(), activityEntity.getTarget(),
					activityEntity.getRegistrationstarttime(), activityEntity.getRegistrationendtime(),
					activityEntity.getIscharge(), activityEntity.getMemo(), activityEntity.getLangs(),
					activityEntity.getFeelist(), activityEntity.getRepeatlist(), activityEntity.getPhotolist(),
					repeatfrequentlist));
		}
		return responseList;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langcode).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(status).");
		}
		if (entity.getType() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(type).");
		}
		if (StringUtils.isBlank(entity.getRepeattype())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat type).");
		}
		if (StringUtils.isBlank(entity.getTarget())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(target).");
		}
		if (StringUtils.isBlank(entity.getCharge())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(charge).");
		}
		if (entity.getWeek() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(week).");
		}
	}

	/**
	 * 列出每個月重複資料,因為不確定到底有多少資料,不新增到DB,確定有人報名在列
	 * 
	 * @param repeatFrequency
	 * @param gson
	 * @return
	 */
	private List<String> repeatFrequencyPrcoess(final String repeatFrequency, final Gson gson) {

		final ActivityRepeatFrequentBean frequencyEntity = gson.fromJson(repeatFrequency,
				ActivityRepeatFrequentBean.class);

		if (frequencyEntity == null) {
			return null;
		}

		final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		List<String> list = new ArrayList<String>();

		for (final String dayStr : frequencyEntity.getDays()) {
			final Calendar calendar = Calendar.getInstance();

			// Calendar的星期 會+1才會對應到正確
			int day = Integer.valueOf(dayStr) + 1;
			if (day > 7) {
				day = 1;
			}

			/**
			 * 系統時間是不是剛好等於指定的星期幾,不是的話就找到下一個最近的 指定星期幾.
			 */
			boolean isPass = calendar.get(Calendar.DAY_OF_WEEK) != day;

			while (isPass) {
				calendar.add(Calendar.DATE, 1);
				if (calendar.get(Calendar.DAY_OF_WEEK) == day) {
					isPass = false;
				}
			}

			int week = entity.getWeek() == 0 ? 12 : entity.getWeek();
			// 列出每周的資料
			while (week-- != 0) {
				list.add(format.format(calendar.getTime()));
				calendar.add(Calendar.DATE, 7);
			}
		}
		Collections.sort(list);
		return list;
	}
}
