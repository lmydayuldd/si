package com.sidc.sits.logical.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.request.ActivityTypeRequest;
import com.sidc.blackcore.api.mobile.activity.response.ActivityTypeResponse;
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
public class ActivityTypeProcess extends AbstractAPIProcess {

	private final ActivityTypeRequest entity;
	private final String STEP = "1";

	public ActivityTypeProcess(final ActivityTypeRequest entity) {
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
		List<ActivityTypeResponse> list = new ArrayList<ActivityTypeResponse>();

		if (entity.getStatus().equals("all")) {
			if (entity.getLangcode().equals("all")) {
				list = ActivityManager.getInstance().selectType();
			} else {
				list = ActivityManager.getInstance().selectType(entity.getLangcode());
			}
			LogAction.getInstance().debug("step 1/" + STEP + " :select success(ActivityManager|selectType).");
		} else {
			int status = -1;

			switch (entity.getLangcode()) {
			case "enabled":
				status = 1;
				break;
			case "disable":
				status = 0;
				break;
			}

			if (entity.getLangcode().equals("all")) {
				ActivityManager.getInstance().selectTypeByStatus(status);
			} else {
				ActivityManager.getInstance().selectTypeByStatus(entity.getLangcode(), status);
			}
			LogAction.getInstance().debug("step 1/" + STEP + " :select success(ActivityManager|selectTypeByStatus).");
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
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of lang code.");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
	}
}
