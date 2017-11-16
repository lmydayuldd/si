package com.sidc.quartz.logical.api;

import org.apache.commons.lang3.StringUtils;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.quartz.manager.ScheduleManager;
import com.sidc.quartz.api.request.ScheduleUpdateDataRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ScheduleUpdateProcess extends AbstractAPIProcess {
	
	private ScheduleUpdateDataRequest enity;
	
	public ScheduleUpdateProcess(ScheduleUpdateDataRequest enity) {
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
		
		ScheduleManager.getInstance().update(enity);
		
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Rquest is illegal");
		}
		
		if (StringUtils.isBlank(this.enity.getJobname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Job name is empty");
		}
		
		if (StringUtils.isBlank(this.enity.getExecutiontime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Schedule executiontime is empty");
		}
	}
}
