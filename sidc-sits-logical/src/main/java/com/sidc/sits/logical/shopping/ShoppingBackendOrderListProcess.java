package com.sidc.sits.logical.shopping;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.request.ShoppingBackendOrderListRequest;
import com.sidc.blackcore.api.sits.shop.response.ShoppingBackendOrderResponse;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ShoppingBackendOrderListProcess extends AbstractAuthAPIProcess {
	private final ShoppingBackendOrderListRequest entity;
	private final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private final String STEP = "2";

	public ShoppingBackendOrderListProcess(final ShoppingBackendOrderListRequest entity) {
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
		String status = null;
		if (!entity.getStatus().equals("all")) {
			status = entity.getStatus();
		}

		boolean selectOption = false;

		String categoryIdWhereInStr = "";
		String vendorIdWhereInStr = "";
		String itemIdWhereInStr = "";

		if (entity.getCategoryid() > 0) {
			selectOption = true;
			categoryIdWhereInStr = String.valueOf(entity.getCategoryid());
		}
		if (entity.getVendorid() > 0) {
			selectOption = true;
			vendorIdWhereInStr = String.valueOf(entity.getVendorid());
		}
		if (entity.getItemid() > 0) {
			selectOption = true;
			itemIdWhereInStr = String.valueOf(entity.getItemid());
		}
		LogAction.getInstance().debug("step 1/" + STEP + " :format where in data success.");

		List<ShoppingBackendOrderResponse> list = new ArrayList<ShoppingBackendOrderResponse>();

		if (selectOption) {
			// 從 order line 篩找到 order id 再用其他條件篩選
			list = ShopManager.getInstance().selectOrderWithBackend(entity.getRoomno(), entity.getOrderid(),
					categoryIdWhereInStr, vendorIdWhereInStr, itemIdWhereInStr, status, entity.getStartime(),
					entity.getEndtime(), entity.getLangcode());
		} else {
			// 直接針對 order header 篩選
			list = ShopManager.getInstance().selectOrderWithBackend(entity.getRoomno(), entity.getOrderid(), status,
					entity.getStartime(), entity.getEndtime(), entity.getLangcode());
		}

		LogAction.getInstance().debug("step 2/" + STEP + " :select success(ShopManager|selectOrderWithBackend).");
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
		if (entity.getCategoryid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(category id).");
		}
		if (entity.getVendorid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(vendor id).");
		}
		if (entity.getItemid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(item id).");
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
		ShopManager.getInstance().backendOrderCheck(entity.getRoomno(), entity.getToken(), entity.getStaffid());
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
