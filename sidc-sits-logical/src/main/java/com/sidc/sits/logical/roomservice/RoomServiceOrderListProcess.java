package com.sidc.sits.logical.roomservice;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderHeaderInfoBean;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceOrderListRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.RoomServiceManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RoomServiceOrderListProcess extends AbstractAPIProcess {
	private final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private final RoomServiceOrderListRequest entity;
	private final String STEP = "1";

	public RoomServiceOrderListProcess(final RoomServiceOrderListRequest entity) {
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
		String status = null;
		if (!entity.getStatus().equals("all")) {
			status = entity.getStatus();
		}
		final List<RoomServiceOrderHeaderInfoBean> list = RoomServiceManager.getInstance().selectOrder(
				entity.getDeviceid(), entity.getOrderid(), status, entity.getStartime(), entity.getEndtime(),
				entity.getLangcode());
		LogAction.getInstance().debug("step 1/" + STEP + " :select success(RoomServiceManager|selectOrder).");
		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status).");
		}
		if (StringUtils.isBlank(entity.getDeviceid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(device id).");
		}
		if (entity.getOrderid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(order id).");
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
