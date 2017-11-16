package com.sidc.sits.logical.shopping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.bean.ShopOrderItemBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingOrderCreateRequest;
import com.sidc.blackcore.api.sits.shop.response.ShoppingBackendOrderResponse;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.PrinterUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ShoppingOrderCreateProcess extends AbstractAuthAPIProcess {

	private final ShoppingOrderCreateRequest entity;
	private final String STEP = "1";

	public ShoppingOrderCreateProcess(final ShoppingOrderCreateRequest entity) {
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
		final int id = ShopManager.getInstance().orderCreate(entity.getPublickey(), entity.getPrivatekey(),
				entity.getRoomno(), entity.getGuestno(), entity.getGuestfirstname(), entity.getGuestlastname(),
				entity.getMemo(), entity.getItemlist());
		LogAction.getInstance().debug("step 1/" + STEP + ":insert success(ShopManager|orderCreate).");

		final String shopOrder = SystemPropertiesManager.getInstance()
				.findPropertiesMessage("enable.shopping.order.printing");
		LogAction.getInstance().debug("shop printing status: " + shopOrder + ".");

		/**
		 * 2017/10/16 老闆指示 下單後就列印 待調整為設定檔!!
		 */
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
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(room no).");
		}
		if (!StringUtils.isBlank(entity.getGuestfirstname()) && entity.getGuestfirstname().length() > 50) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(guest fist name length).");
		}
		if (!StringUtils.isBlank(entity.getGuestlastname()) && entity.getGuestlastname().length() > 50) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(guest last name length).");
		}
		if (!StringUtils.isBlank(entity.getMemo()) && entity.getMemo().length() > 1024) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(memo length).");
		}
		if (entity.getItemlist() == null || entity.getItemlist().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item list).");
		}
		List<Integer> itemIdList = new ArrayList<Integer>();
		for (final ShopOrderItemBean itemEntity : entity.getItemlist()) {
			if (itemEntity.getItemid() < 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item id).");
			}
			if (itemEntity.getQty() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(qty).");
			}
			itemIdList.add(itemEntity.getItemid());
		}

		ShopManager.getInstance().orderCreateCheck(entity.getRoomno(), entity.getGuestno(), itemIdList);
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
		final String printStep = "4";
		LogAction.getInstance().debug("start printer process.");

		String langCode = SystemPropertiesManager.getInstance()
				.findPropertiesMessage("system.printer.langcode.purchase");
		LogAction.getInstance().debug("print step 1/" + printStep + ":lang code=" + langCode);

		if (StringUtils.isBlank(langCode)) {
			langCode = "en_US";
			LogAction.getInstance().warn("not find printe lang code(system.printer.langcode.purchase).");
		}

		// 列印!!
		List<ShoppingBackendOrderResponse> list = ShopManager.getInstance().selectOrderWithBackend(null, id, null, null,
				null, langCode);

		// 沒資料 用英文去撈
		if (list.isEmpty()) {
			list = ShopManager.getInstance().selectOrderWithBackend(null, id, null, null, null, "en_US");
		}

		if (list.isEmpty()) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find order data.");
		}
		LogAction.getInstance().debug("print step 2/" + printStep + ":get data success.");
		// 套表
		PrinterUtils printerUtils = new PrinterUtils();
		String format = printerUtils.replaceTable(list.get(0));
		LogAction.getInstance().debug("print step 3/" + printStep + ":format data.");

		// 列印
		printerUtils.printer(format);
		LogAction.getInstance().debug("print step 4/" + printStep + ":send to sits.");
	}
}
