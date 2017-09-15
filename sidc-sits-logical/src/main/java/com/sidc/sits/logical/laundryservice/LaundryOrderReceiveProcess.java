package com.sidc.sits.logical.laundryservice;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryOrderReceiveRequest;
import com.sidc.dao.sits.manager.LaundryServiceManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class LaundryOrderReceiveProcess extends AbstractAuthAPIProcess {

	private final LaundryOrderReceiveRequest entity;
	private final String STEP = "1";

	public LaundryOrderReceiveProcess(final LaundryOrderReceiveRequest entity) {
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
		LaundryServiceManager.getInstance().updateOrderReceiveTime(entity.getId(), entity.getReceivetime());
		LogAction.getInstance()
				.debug("step 1/" + STEP + " :select success(LaundryServiceManager|updateOrderReceiveTime).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of token.");
		}
		if (entity.getId() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of id.");
		}
		if (StringUtils.isBlank(entity.getReceivetime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of receive time.");
		}
		if (!isDate(entity.getReceivetime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
					"illegal of request(receive time not datetime format yyyy/MM/dd HH:mm).");
		}
		if (!LaundryServiceManager.getInstance().isExistOfHeaderId(entity.getId())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find id.");
		}
	}

	private boolean isDate(final String dttm) {
		final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
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
}
