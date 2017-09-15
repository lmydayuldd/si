package com.sidc.rcu.hmi.logical.schedule;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.request.schedule.ScheduleCommandRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ScheduleCommandProcess extends AbstractAPIProcess {
	private final ScheduleCommandRequest entity;

	public ScheduleCommandProcess(final ScheduleCommandRequest entity) {
		// TODO Auto-generated constructor stub
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		LogAction.getInstance().debug("Request:" + entity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		// new ScheduleCheckOutUpdateClient("http://10.60.1.39:7056/blackcore",
		// UDPParser.getInstance().toJsonByContent(entity)).execute();

		LogAction.getInstance().debug("send to blackcore success(sdk|ScheduleCommandClient).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is null.");
		}
		if (StringUtils.isBlank(entity.getCommand())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(command).");
		}
		if (StringUtils.isBlank(entity.getJobgroup())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(job group).");
		}
		if (StringUtils.isBlank(entity.getJobname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(job name).");
		}
	}
}
