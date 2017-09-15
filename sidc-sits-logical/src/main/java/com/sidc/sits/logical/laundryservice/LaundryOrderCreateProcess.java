package com.sidc.sits.logical.laundryservice;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryOrderItemBean;
import com.sidc.blackcore.api.mobile.laundry.request.LaundryOrderCreateRequest;
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
public class LaundryOrderCreateProcess extends AbstractAuthAPIProcess {

	private final LaundryOrderCreateRequest entity;
	private final String STEP = "1";

	public LaundryOrderCreateProcess(final LaundryOrderCreateRequest entity) {
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

		final int id = LaundryServiceManager.getInstance().createOrder(entity.getPublickey(), entity.getPrivatekey(),
				entity.getRoomno(), entity.getGuestno(), entity.getGuestfirstname(), entity.getGuestlastname(),
				entity.getReceivetime(), entity.getClassid(), entity.getMemo(), entity.getItemlist());
		LogAction.getInstance().debug("step 1/" + STEP + " :insert success(LaundryServiceManager|createOrder).");

		return id;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of public key.");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of private key.");
		}
		if (entity.getClassid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of class id.");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
		}
		if (!StringUtils.isBlank(entity.getGuestfirstname()) && entity.getGuestfirstname().length() > 50) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of guest first name length.");
		}
		if (StringUtils.isBlank(entity.getGuestlastname()) && entity.getGuestlastname().length() > 50) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of guest first name length.");
		}
		if (!StringUtils.isBlank(entity.getReceivetime()) && !isDate(entity.getReceivetime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
					"illegal of request(receive time not datetime format yyyy/MM/dd HH:mm).");
		}
		if (entity.getItemlist() == null || entity.getItemlist().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of item list.");
		}
		List<Integer> itemIdList = new ArrayList<Integer>();
		List<Integer> returnTypeIdList = new ArrayList<Integer>();
		List<Integer> washTypeIdList = new ArrayList<Integer>();
		for (final LaundryOrderItemBean itemEntity : entity.getItemlist()) {
			if (itemEntity.getItemid() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of item id.");
			}
			if (itemEntity.getPiece() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of piece.");
			}
			if (itemEntity.getReturntypeid() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of return type id.");
			}
			if (itemEntity.getWashtypeid() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of wash type id.");
			}
			itemIdList.add(itemEntity.getItemid());
			returnTypeIdList.add(itemEntity.getReturntypeid());
			washTypeIdList.add(itemEntity.getWashtypeid());
		}

		if (entity.getMemo().length() > 1024) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of memo length.");
		}
		LaundryServiceManager.getInstance().orderCreateCheck(entity.getRoomno(), entity.getClassid(),
				entity.getGuestno(), itemIdList, returnTypeIdList, washTypeIdList);
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
