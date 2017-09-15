package com.sidc.quartz.logical.api;

import org.apache.commons.lang3.StringUtils;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.quartz.manager.ScheduleManager;
import com.sidc.quartz.api.request.ScheduleStatusRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ScheduleStatusProcess extends AbstractAPIProcess {
	
	private final ScheduleStatusRequest entity;

	public ScheduleStatusProcess(final ScheduleStatusRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		return ScheduleManager.getInstance().enabled(entity.getJobname());
	}

	@Override
	protected void check() throws SiDCException, Exception {
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is null.");
		}
		if (StringUtils.isBlank(entity.getJobname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(job name).");
		}
	}
}
