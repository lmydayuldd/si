package com.sidc.sits.logical.shopping;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.mobile.message.bean.OrderStatusUpdateBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingOrderStatusRequest;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.FcmUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ShoppingOrderStatusProcess extends AbstractAuthAPIProcess {

	private final ShoppingOrderStatusRequest entity;
	private final String STEP = "2";

	public ShoppingOrderStatusProcess(final ShoppingOrderStatusRequest entity) {
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
		final String statusStr = ShopManager.getInstance().selectOrderStatus(entity.getOrderid());
		LogAction.getInstance()
				.debug("step 1/" + STEP + ":get old status(ShopManager|selectOrderStatus)" + statusStr + ".");

		final int oldStatus = Integer.valueOf(statusStr);
		final int newStatus = Integer.valueOf(entity.getStatus());

		// 負數 為訂單取消 等等
		if (newStatus >= 0) {
			if (oldStatus > newStatus) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status sequence).");
			}
		}

		if (entity.getStatus().equals("10")) {
			ShopManager.getInstance().updateOrderStatusCreateCons(entity.getOrderid(), entity.getStatus());
		} else if (entity.getStatus().equals("-10")) {
			ShopManager.getInstance().updateOrderStatusDeleteCons(entity.getOrderid(), entity.getStatus());
		} else {
			ShopManager.getInstance().updateOrderStatus(entity.getOrderid(), entity.getStatus());

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
		if (!ShopManager.getInstance().isExistByOrderId(entity.getOrderid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find order id).");
		}
	}

	private void fcmDataProcess() throws SQLException, SiDCException {
		final String fcmStep = "2";
		LogAction.getInstance().debug("start fcm process.");

		final OrderStatusUpdateBean bean = new OrderStatusUpdateBean("SHP", "STATUS_UPDATE",
				String.valueOf(entity.getOrderid()), entity.getStatus());

		final String pushToken = ShopManager.getInstance().findOrderMobilePushToken(entity.getOrderid());

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
