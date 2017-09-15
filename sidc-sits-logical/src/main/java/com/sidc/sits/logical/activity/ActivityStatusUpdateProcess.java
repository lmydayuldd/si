package com.sidc.sits.logical.activity;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.request.ActivityStatusUpdateRequest;
import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.mobile.message.bean.OrderStatusUpdateBean;
import com.sidc.dao.sits.manager.ActivityManager;
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
public class ActivityStatusUpdateProcess extends AbstractAuthAPIProcess {

	private final ActivityStatusUpdateRequest entity;
	private final String STEP = "1";

	public ActivityStatusUpdateProcess(final ActivityStatusUpdateRequest entity) {
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
		ActivityManager.getInstance().updateOrderStatus(entity.getOrderid(), entity.getStatus());
		try {
			fcmDataProcess();
		} catch (Exception e) {
			LogAction.getInstance().warn("send to fcm fail:" + e.getMessage());
		}
		LogAction.getInstance().debug("step 1/" + STEP + " :update success(ActivityManager|updateOrderStatus).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(token).");
		}
		if (entity.getOrderid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(order id).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(status).");
		}
		if (!ActivityManager.getInstance().isExistOfOrderID(entity.getOrderid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "order id not existed.");
		}
	}

	private void fcmDataProcess() throws SQLException, SiDCException {
		final String fcmStep = "2";
		LogAction.getInstance().debug("start fcm process.");
		final OrderStatusUpdateBean bean = new OrderStatusUpdateBean("ACT", "STATUS_UPDATE",
				String.valueOf(entity.getOrderid()), entity.getStatus());

		final String pushToken = ActivityManager.getInstance().findOrderMobilePushToken(entity.getOrderid());

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
