package com.sidc.sits.logical.activity;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.request.ActivityBackendOrderListRequest;
import com.sidc.blackcore.api.mobile.activity.response.ActivityOrderListResponse;
import com.sidc.dao.sits.manager.ActivityManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ActivityBackendOrderListProcess extends AbstractAuthAPIProcess {
	private final ActivityBackendOrderListRequest entity;
	private final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private final String STEP = "2";

	public ActivityBackendOrderListProcess(final ActivityBackendOrderListRequest entity) {
		super(entity.getToken());
		this.entity = entity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		String status = null, paymentStatus = null;
		if (!entity.getStatus().equals("all")) {
			status = entity.getStatus();
		}
		if (!entity.getPaymentstatus().equals("all")) {
			paymentStatus = entity.getPaymentstatus();
		}

		String feeIdStr = null;
		boolean selectOption = false;

		if (entity.getFeeid() > 0) {
			feeIdStr = String.valueOf(entity.getFeeid());
			selectOption = true;
		}
		if (!StringUtils.isBlank(entity.getIdentitytype())) {
			selectOption = true;
		}

		List<ActivityOrderListResponse> list = new ArrayList<ActivityOrderListResponse>();

		if (selectOption) {
			// // 從 order line 篩找到 order id 再用其他條件篩選
			list = ActivityManager.getInstance().selectOrderWithBackend(entity.getActivityid(),
					entity.getActivityrepeatid(), entity.getOrderid(), status, paymentStatus, entity.getRoomno(),
					entity.getStartime(), entity.getEndtime(), entity.getLangcode(), feeIdStr,
					"'" + entity.getIdentitytype() + "'");
		} else {
			// 直接針對 order header 篩選
			list = ActivityManager.getInstance().selectOrderWithBackend(entity.getActivityid(),
					entity.getActivityrepeatid(), entity.getOrderid(), status, paymentStatus, entity.getRoomno(),
					entity.getStartime(), entity.getEndtime(), entity.getLangcode());
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :select success(ActivityManager|selectOrderWithBackend).");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(token).");
		}
		if (StringUtils.isBlank(entity.getStaffid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(staff id).");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(lang code).");
		}
		if (entity.getOrderid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(order id).");
		}
		if (entity.getActivityid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(activity id).");
		}
		if (entity.getActivityrepeatid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(activity repeat id).");
		}
		if (StringUtils.isBlank(entity.getPaymentstatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(payment status).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(status).");
		}
		if (!StringUtils.isBlank(entity.getStartime()) && !isDate(entity.getStartime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
					"illegal of request(start time not datetime format yyyy/MM/dd HH:mm).");
		}
		if (!StringUtils.isBlank(entity.getEndtime()) && !isDate(entity.getEndtime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
					"illegal of request(end time not datetime format yyyy/MM/dd HH:mm).");
		}
		if (!StringUtils.isBlank(entity.getStartime()) && !StringUtils.isBlank(entity.getEndtime())) {
			if (!checkSequence(entity.getStartime(), entity.getEndtime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(registration time sequence error).");
			}
		}

		ActivityManager.getInstance().backendOrderCheck(entity.getRoomno(), entity.getToken(), entity.getStaffid());
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
