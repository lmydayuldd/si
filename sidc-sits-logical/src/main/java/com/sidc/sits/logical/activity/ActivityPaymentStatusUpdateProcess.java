package com.sidc.sits.logical.activity;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.request.ActivityPaymentStatusUpdateRequest;
import com.sidc.dao.sits.manager.ActivityManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ActivityPaymentStatusUpdateProcess extends AbstractAuthAPIProcess {

	private final ActivityPaymentStatusUpdateRequest entity;
	private final String STEP = "1";

	public ActivityPaymentStatusUpdateProcess(final ActivityPaymentStatusUpdateRequest entity) {
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
		ActivityManager.getInstance().updateOrderPaymentStatus(entity.getOrderid(), entity.getPaymentstatus());
		LogAction.getInstance().debug("step 1/" + STEP + " :insert success(ActivityManager|updateOrderPaymentStatus).");

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
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(activity id).");
		}
		if (StringUtils.isBlank(entity.getPaymentstatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of requet(payment status).");
		}

		ActivityManager.getInstance().paymentStatusUpdateCheck(entity.getOrderid());

	}
}
