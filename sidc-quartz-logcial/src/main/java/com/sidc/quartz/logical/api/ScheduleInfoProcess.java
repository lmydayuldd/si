package com.sidc.quartz.logical.api;

import org.apache.commons.lang3.StringUtils;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.quartz.manager.ScheduleManager;
import com.sidc.quartz.api.request.ScheduleInfoRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ScheduleInfoProcess extends AbstractAPIProcess {

	private final ScheduleInfoRequest enity;
	
	public ScheduleInfoProcess(ScheduleInfoRequest enity) {
		// TODO Auto-generated constructor stub
		this.enity = enity;
	}
	
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + this.enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		return ScheduleManager.getInstance().select(enity.getJobname());
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is null.");
		}
		if (StringUtils.isBlank(this.enity.getJobname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(job name).");
		}
	}

}
