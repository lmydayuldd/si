package com.sidc.sits.logical.roomservice;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceBackendOrderInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderSetBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderSetItemBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemListBean;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCreateOrderRequest;
import com.sidc.dao.sits.manager.RoomServiceManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.DateTimeUtils;
import com.sidc.sits.logical.utils.PrinterUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RoomServiceOrderCreateProcess extends AbstractAuthAPIProcess {

	private final RoomServiceCreateOrderRequest entity;
	private final String STEP = "1";

	public RoomServiceOrderCreateProcess(final RoomServiceCreateOrderRequest entity) {
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno());
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
		final int id = RoomServiceManager.getInstance().createOrder(entity.getRoomno(), entity.getGuestno(),
				entity.getGuestfirstname(), entity.getGuestlastname(), entity.getExpectedtime(), entity.getMemo(),
				entity.getList(), entity.getSetlist(), entity.getPublickey(), entity.getPrivatekey());
		LogAction.getInstance().debug("step 1/" + STEP + ":insert success(RoomServiceManager|createOrder).");

		/**
		 * 2017/10/16 老闆指示 下單後就列印 待調整為設定檔!!
		 */
		final String shopOrder = SystemPropertiesManager.getInstance()
				.findPropertiesMessage("enable.shopping.order.printing");
		LogAction.getInstance().debug("shop printing status: " + shopOrder + ".");

		// sits後臺要設定
		if (shopOrder.equals("Y")) {
			try {
				printerProcess(id);
				LogAction.getInstance().debug("send to pinter success.");
			} catch (IOException e) {
				LogAction.getInstance().warn("send to pinter fial:" + e.getMessage());
			}
		}

		return id;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(public key).");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(private key).");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(room).");
		}
		if (!StringUtils.isBlank(entity.getGuestfirstname()) && entity.getGuestfirstname().length() > 30) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(guest fist name length).");
		}
		if (!StringUtils.isBlank(entity.getGuestlastname()) && entity.getGuestlastname().length() > 30) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(guest last name length).");
		}
		if (!StringUtils.isBlank(entity.getExpectedtime())) {
			final Date date = new Date();
			final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			final DateTimeUtils dateUtils = new DateTimeUtils(formatter);

			if (!dateUtils.isDate(entity.getExpectedtime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(expected time not datetime format yyyy/MM/dd HH:mm).");
			}
			if (!dateUtils.checkSequence(formatter.format(date), entity.getExpectedtime())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(expected time is less than the system time.");
			}
		} else {
			entity.setExpectedtime(null);
		}
		if (!StringUtils.isBlank(entity.getMemo()) && entity.getMemo().length() > 1024) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(memo length).");
		}
		if ((entity.getList() == null || entity.getList().isEmpty())
				&& (entity.getSetlist() == null || entity.getSetlist().isEmpty())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(list,set list).");
		}
		List<Integer> itemList = new ArrayList<Integer>();
		if (entity.getList() != null) {
			for (final RoomServiceOrderLineBean itemEntity : entity.getList()) {
				if (itemEntity.getItemid() <= 0) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item id).");
				}
				if (itemEntity.getQty() <= 0) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(qty).");
				}
				if (itemList.contains(itemEntity.getItemid())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item id repeat).");
				}
				itemList.add(itemEntity.getItemid());
			}
		} else {
			entity.setList(new ArrayList<RoomServiceOrderLineBean>());
		}
		// 這邊要先檢查,下面會把 set item 也列進去
		if (!itemList.isEmpty() && !RoomServiceManager.getInstance().isSingleWithItem(itemList)) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item id is not single type).");
		}
		if (entity.getSetlist() != null) {
			if (!entity.getSetlist().isEmpty()) {
				for (final RoomServiceOrderSetBean setEntity : entity.getSetlist()) {
					if (setEntity.getSetitemid() <= 0) {
						throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(set item id).");
					}
					if (setEntity.getQty() <= 0) {
						throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(set list qty).");
					}
					if (setEntity.getQty() != setEntity.getIteminfo().size()) {
						throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
								"request illegal(set list qty count not equals item info size).");
					}
					for (final RoomServiceSetItemListBean itemListEntity : setEntity.getIteminfo()) {
						List<Integer> setItemList = new ArrayList<Integer>();
						for (final RoomServiceOrderSetItemBean itemEntity : itemListEntity.getItemlist()) {
							if (itemEntity.getItemid() <= 0) {
								throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
										"request illegal(set list item id).");
							}
							if (setItemList.contains(itemEntity.getItemid())) {
								throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
										"request illegal(set list item id repeat).");
							}
							setItemList.add(itemEntity.getItemid());

							if (!itemList.contains(itemEntity.getItemid()))
								itemList.add(itemEntity.getItemid());
						}
					}
					itemList.add(setEntity.getSetitemid());
				}
				if (!RoomServiceManager.getInstance().isExistBySet(entity.getSetlist())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(set list item not match).");
				}
			}
		} else {
			entity.setSetlist(new ArrayList<RoomServiceOrderSetBean>());
		}

		RoomServiceManager.getInstance().orderCreateCheck(entity.getRoomno(), entity.getGuestno(), itemList);
	}

	/**
	 * 通知sits印東西
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws SiDCException
	 * @throws IOException
	 */
	private void printerProcess(final int id) throws SQLException, SiDCException, IOException {
		final String printStep = "5";
		LogAction.getInstance().debug("start printer process.");

		String langCode = SystemPropertiesManager.getInstance()
				.findPropertiesMessage("system.printer.langcode.purchase");
		LogAction.getInstance().debug("print step 1/" + printStep + ":lang code=" + langCode);

		if (StringUtils.isBlank(langCode)) {
			langCode = "en_US";
			LogAction.getInstance().warn("not find printe lang code(system.printer.langcode.purchase).");
		}

		// 列印!!
		List<RoomServiceBackendOrderInfoBean> list = RoomServiceManager.getInstance().selectOrderWithBackend(null, id,
				null, null, null, langCode);

		// 沒資料 用英文去撈
		if (list.isEmpty()) {
			list = RoomServiceManager.getInstance().selectOrderWithBackend(null, id, null, null, null, "en_US");
		}

		if (list.isEmpty()) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find order data.");
		}
		LogAction.getInstance().debug("print step 2/" + printStep + ":get data success.");

		list = handleItemSet(list);
		LogAction.getInstance().debug("print step 3/" + printStep + ":handle item set data success.");

		// 套表
		PrinterUtils printerUtils = new PrinterUtils();
		String format = printerUtils.replaceTable(list.get(0));
		LogAction.getInstance().debug("print step 4/" + printStep + ":format data.");

		// 列印
		printerUtils.printer(format);
		LogAction.getInstance().debug("print step 5/" + printStep + ":send to sits.");
	}

	/**
	 * 把有 set的資料 列出詳細的item
	 * 
	 * @param list
	 * @return
	 */
	private List<RoomServiceBackendOrderInfoBean> handleItemSet(List<RoomServiceBackendOrderInfoBean> list) {
		for (final RoomServiceBackendOrderInfoBean orderEntity : list) {
			final List<RoomServiceOrderLineInfoBean> newItems = new ArrayList<RoomServiceOrderLineInfoBean>();
			newItems.addAll(orderEntity.getItemlist());

			for (final RoomServiceOrderLineInfoBean itemEntity : orderEntity.getItemlist()) {
				// 有set的資料 要把 所有項目列出來
				if (!itemEntity.getSetitemlist().isEmpty()) {
					newItems.addAll(itemEntity.getSetitemlist());
				}
			}
			orderEntity.setItemlist(newItems);
		}
		return list;
	}

}
