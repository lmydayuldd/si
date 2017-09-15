package com.sidc.sits.logical.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatFrequentBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivitySignUpBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivitySignUpRequest;
import com.sidc.dao.sits.manager.ActivityManager;
import com.sidc.dao.sits.manager.GuestManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.DateTimeUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ActivityOrderCreateProcess extends AbstractAuthAPIProcess {

	private final ActivitySignUpRequest entity;
	private final String STEP = "1";

	public ActivityOrderCreateProcess(final ActivitySignUpRequest entity) {
		// TODO Auto-generated constructor stub
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno());
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		final int id = ActivityManager.getInstance().signUp(entity.getPublickey(), entity.getPrivatekey(),
				entity.getRoomno(), entity.getList(), entity.getActivityid(), entity.getRepeatid(), entity.getMemo(),
				entity.getActivitydate());
		LogAction.getInstance().debug("step 1/" + STEP + " :insert success(ActivityManager|signUp).");

		return id;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(public key).");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(private key).");
		}
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of list.");
		}
		if (entity.getActivityid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(activity id).");
		}
		if (entity.getRepeatid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(repeat id).");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(room no).");
		}
		if (!StringUtils.isBlank(entity.getMemo()) && entity.getMemo().length() > 1024) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(memo length).");
		}

		List<Integer> feeList = new ArrayList<Integer>();
		List<String> idList = new ArrayList<String>();
		List<String> passportList = new ArrayList<String>();
		Map<String, List<String>> roomMap = new HashMap<String, List<String>>();
		for (final ActivitySignUpBean signUpEntity : entity.getList()) {
			if (StringUtils.isBlank(signUpEntity.getFirstname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(first name).");
			}
			if (StringUtils.isBlank(signUpEntity.getLastname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(last name).");
			}
			if (signUpEntity.getSex() < 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(sex).");
			}
			if (signUpEntity.getFeeid() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(fee id).");
			}
			feeList.add(signUpEntity.getFeeid());
			if (StringUtils.isBlank(signUpEntity.getIdentitytype())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(identity type).");
			}
			if (!StringUtils.isBlank(signUpEntity.getGuestno())) {
				List<String> guestList = new ArrayList<String>();
				if (roomMap.containsKey(entity.getRoomno())) {
					guestList = roomMap.get(entity.getRoomno());
				}
				guestList.add(signUpEntity.getGuestno());
				roomMap.put(entity.getRoomno(), guestList);
			}
			if (signUpEntity.getLastname().length() > 25) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(last name length).");
			}
			if (signUpEntity.getFirstname().length() > 25) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(first name length).");
			}
			if (!StringUtils.isBlank(signUpEntity.getIdentity())) {
				if (signUpEntity.getIdentity().length() > 25) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(identity length).");
				}
				idList.add(signUpEntity.getIdentity());
			}
			if (!StringUtils.isBlank(signUpEntity.getPassport())) {
				if (signUpEntity.getPassport().length() > 25) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(passport length).");
				}
				passportList.add(signUpEntity.getPassport());
			}
			if (!StringUtils.isBlank(signUpEntity.getEmail()) && signUpEntity.getEmail().length() > 150) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(email length).");
			}
			if (!StringUtils.isBlank(signUpEntity.getPhone()) && signUpEntity.getPhone().length() > 15) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(phone length).");
			}
		}

		ActivityManager.getInstance().orderCreateCheck(entity.getRoomno(), entity.getActivityid(), entity.getRepeatid(),
				entity.getList().size(), feeList, idList, passportList);

		List<String> guestNoNotExistList = GuestManager.getInstance().isExist(roomMap);
		if (!guestNoNotExistList.isEmpty()) {
			String guestStr = "";
			for (final String guestNo : guestNoNotExistList) {
				guestStr += guestNo + " ";
			}
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
					"illegal of request(guest no not existed " + guestStr + ").");
		}
		final Date date = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		final DateTimeUtils dateUtils = new DateTimeUtils(formatter);
		final String repeatType = ActivityManager.getInstance().getRepeatType(entity.getActivityid());
		final String starTime = ActivityManager.getInstance().selectRepeatStarTime(entity.getActivityid(),
				entity.getRepeatid());

		if (repeatType.equals("3")) {
			if (StringUtils.isBlank(entity.getActivitydate())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(activity date).");
			}

			if (!dateUtils.isDate(entity.getActivitydate())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(activity date not datetime format yyyy/MM/dd).");
			}
			if (!dateUtils.checkSequence(formatter.format(date), entity.getActivitydate())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(activity date is less than the system time.");
			}
			if (!checkRepeatFrequent(ActivityManager.getInstance().selectRepeatFrequent(entity.getActivityid()))) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(not find this activity date.");
			}

			dateUtils.setDateFromat(new SimpleDateFormat("yyyy/MM/dd HH:mm"));
			if (!dateUtils.checkSequence(formatter.format(date), entity.getActivitydate() + starTime.substring(10))) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(activity date is less than the star date.");
			}
		} else {
			formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			dateUtils.setDateFromat(formatter);
			if (!dateUtils.checkSequence(formatter.format(date), starTime)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(activity date is less than the star date.");
			}
		}
		if (roomMap.isEmpty()) {// 沒有 guest資料 用room 來驗證 退房時間
			if (ActivityManager.getInstance().isOverRoomDepartureDate(entity.getActivityid(), entity.getRepeatid(),
					entity.getRoomno())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "activity start time over departure date.");
			}
		}
	}

	private boolean checkRepeatFrequent(final String json) throws Exception {
		boolean isPass = false;
		final Gson gson = new Gson();
		final ActivityRepeatFrequentBean frequentEntity = gson.fromJson(json, ActivityRepeatFrequentBean.class);

		final Calendar calendar = Calendar.getInstance();
		final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		calendar.setTime(format.parse(entity.getActivitydate()));

		for (final String dayStr : frequentEntity.getDays()) {
			int day = Integer.valueOf(dayStr) + 1;
			if (day > 7) {
				day = 1;
			}
			isPass = calendar.get(Calendar.DAY_OF_WEEK) == day;

			if (isPass) {
				return true;
			}
		}

		return isPass;
	}
}
