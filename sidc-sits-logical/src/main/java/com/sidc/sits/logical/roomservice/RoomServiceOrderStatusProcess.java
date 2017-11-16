package com.sidc.sits.logical.roomservice;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.mobile.message.bean.OrderStatusUpdateBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceBackendOrderInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineInfoBean;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceOrderStatusRequest;
import com.sidc.configuration.conf.Env;
import com.sidc.dao.sits.manager.RoomServiceManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.FcmUtils;
import com.sidc.sits.logical.utils.PrinterUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RoomServiceOrderStatusProcess extends AbstractAuthAPIProcess {

	private final RoomServiceOrderStatusRequest entity;
	private final String STEP = "2";

	public RoomServiceOrderStatusProcess(final RoomServiceOrderStatusRequest entity) {
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
		final String statusStr = RoomServiceManager.getInstance().selectOrderStatus(entity.getOrderid());
		LogAction.getInstance()
				.debug("step 1/" + STEP + ":get old status(RoomServiceManager|selectOrderStatus)" + statusStr + ".");

		final int oldStatus = Integer.valueOf(statusStr);
		final int newStatus = Integer.valueOf(entity.getStatus());

		// 負數 為訂單取消 等等
		if (newStatus >= 0) {
			if (oldStatus > newStatus) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status sequence).");
			}
		}

		if (entity.getStatus().equals("10")) {
			RoomServiceManager.getInstance().updateOrderStatusCreateCons(entity.getOrderid(), entity.getStatus());
			/**
			 * 2017/10/16 暫時先在 create order 上就產生
			 * 
			 * final String shopOrder = SystemPropertiesManager.getInstance()
			 * .findPropertiesMessage("enable.shopping.order.printing");
			 * LogAction.getInstance().debug("shop printing status: " +
			 * shopOrder + ".");
			 * 
			 * // sits後臺要設定 if (shopOrder.equals("Y")) { try { printerProcess();
			 * } catch (IOException e) { LogAction.getInstance().warn(
			 * "send to pinter fial:" + e.getMessage()); } }
			 * 
			 */

		} else if (entity.getStatus().equals("-10")) {
			RoomServiceManager.getInstance().updateOrderStatusDeleteCons(entity.getOrderid(), entity.getStatus());
		} else {
			RoomServiceManager.getInstance().updateOrderStatus(entity.getOrderid(), entity.getStatus());
		}

		try {
			fcmDataProcess();
		} catch (Exception e) {
			LogAction.getInstance().warn("send to fcm fail:" + e.getMessage());
		}

		LogAction.getInstance().debug("step 2/" + STEP + ":update success.");

		return null;
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
		if (entity.getOrderid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(order id).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status).");
		}
		if (!RoomServiceManager.getInstance().isExistByOrderId(entity.getOrderid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find order id).");
		}
	}

	private void printerProcess() throws SQLException, SiDCException, IOException {
		final String printStep = "3";
		LogAction.getInstance().debug("start printer process.");
		// 列印!!
		final List<RoomServiceBackendOrderInfoBean> list = RoomServiceManager.getInstance().selectOrderWithBackend(null,
				entity.getOrderid(), null, null, null, "en_US");
		LogAction.getInstance().debug("print step 1/" + printStep + ":get data success.");

		PrinterUtils printerUtils = new PrinterUtils();
		// 套表
		String format = printerUtils.replaceTable(list.get(0));
		LogAction.getInstance().debug("print step 2/" + printStep + ":format data.");

		// 列印
		printerUtils.printer(format);
		LogAction.getInstance().debug("print step 3/" + printStep + ":send to sits.");
	}

	/**
	 * 整理 要發送 FCM資料
	 * 
	 * @throws SQLException
	 * @throws SiDCException
	 */
	private void fcmDataProcess() throws SQLException, SiDCException {
		final String fcmStep = "2";
		LogAction.getInstance().debug("start fcm process.");
		final OrderStatusUpdateBean bean = new OrderStatusUpdateBean("IN", "STATUS_UPDATE",
				String.valueOf(entity.getOrderid()), entity.getStatus());

		final String pushToken = RoomServiceManager.getInstance().findOrderMobilePushToken(entity.getOrderid());

		if (StringUtils.isBlank(pushToken)) {
			LogAction.getInstance().debug("this order does not have push token");
			return;
		}

		final FcmBean enity = new FcmBean(Arrays.asList(pushToken), bean);
		LogAction.getInstance().debug("fcm step 1/" + fcmStep + ":format data.");

		new FcmUtils().sendFcmProcess(enity);
		LogAction.getInstance().debug("fcm step 2/" + fcmStep + ":send fcm.");
	}

}
