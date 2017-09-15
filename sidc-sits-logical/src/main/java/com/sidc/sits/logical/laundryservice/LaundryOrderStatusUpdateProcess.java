package com.sidc.sits.logical.laundryservice;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryOrderStatusUpdateRequest;
import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.mobile.message.bean.OrderStatusUpdateBean;
import com.sidc.dao.sits.manager.LaundryServiceManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.FcmUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class LaundryOrderStatusUpdateProcess extends AbstractAuthAPIProcess {

	private final LaundryOrderStatusUpdateRequest entity;
	private final String STEP = "2";

	public LaundryOrderStatusUpdateProcess(final LaundryOrderStatusUpdateRequest entity) {
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
		final String statusStr = LaundryServiceManager.getInstance().selectOrderStatus(entity.getOrderid());
		LogAction.getInstance()
				.debug("step 1/" + STEP + ":get status(LaundryServiceManager|selectOrderStatus)" + statusStr + ".");

		final int oldStatus = Integer.valueOf(statusStr);
		final int newStatus = Integer.valueOf(entity.getStatus());

		// 負數 為訂單取消 等等
		if (newStatus >= 0) {
			if (oldStatus > newStatus) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status sequence).");
			}
		}

		if (entity.getStatus().equals("10")) {
			LaundryServiceManager.getInstance().updateOrderStatusCreateCons(entity.getOrderid(), entity.getStatus());
		} else if (entity.getStatus().equals("-10")) {
			LaundryServiceManager.getInstance().updateOrderStatusDeleteCons(entity.getOrderid(), entity.getStatus());
		} else {
			LaundryServiceManager.getInstance().updateOrderStatus(entity.getOrderid(), entity.getStatus());
		}
		try {
			fcmDataProcess();
		} catch (Exception e) {
			LogAction.getInstance().warn("send to fcm fail:" + e.getMessage());
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :update status success.");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getOrderid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of order id.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of token.");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (entity.getStatus() == "10") {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status(lost receive time).");
		}
		if (!LaundryServiceManager.getInstance().isExistOfHeaderId(entity.getOrderid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find order id.");
		}
	}

	private void fcmDataProcess() throws SQLException, SiDCException {
		final String fcmStep = "2";
		LogAction.getInstance().debug("start fcm process.");
		final OrderStatusUpdateBean bean = new OrderStatusUpdateBean("LD", "STATUS_UPDATE",
				String.valueOf(entity.getOrderid()), entity.getStatus());

		final String pushToken = LaundryServiceManager.getInstance().findOrderMobilePushToken(entity.getOrderid());

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
