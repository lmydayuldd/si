package com.sidc.sits.logical.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.request.ActivityFeeRequest;
import com.sidc.blackcore.api.mobile.activity.response.ActivityFeeResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.ActivityManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ActivityFeeProcess extends AbstractAPIProcess {
	private final ActivityFeeRequest entity;
	private final String STEP = "1";

	public ActivityFeeProcess(final ActivityFeeRequest entity) {
		// TODO Auto-generated constructor stub
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		List<ActivityFeeResponse> list = new ArrayList<ActivityFeeResponse>();

		if (entity.getStatus().equals("all")) {
			if (entity.getLangcode().equals("all"))
				list = ActivityManager.getInstance().selectFee();
			else
				list = ActivityManager.getInstance().selectFee(entity.getLangcode());

			LogAction.getInstance().debug("step 1/" + STEP + " :select success.");
		} else {
			int status = -1;

			switch (entity.getStatus()) {
			case "enabled":
				status = 1;
				break;
			case "disable":
				status = 0;
				break;
			}

			if (entity.getLangcode().equals("all"))
				list = ActivityManager.getInstance().selectFeeByStatus(status);
			else
				list = ActivityManager.getInstance().selectFeeByStatus(entity.getLangcode(), status);

			LogAction.getInstance().debug("step 1/" + STEP + " :select success(ActivityManager|selectByStatus).");
		}

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langcode.");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
	}
}
