package com.sidc.rcu.hmi.logical.schedule;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.request.schedule.ScheduleCheckOutUpdateRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.schedule.ScheduleCheckOutUpdateClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ScheduleCheckOutUpdateProcess extends AbstractAPIProcess {
	private final ScheduleCheckOutUpdateRequest entity;

	public ScheduleCheckOutUpdateProcess(final ScheduleCheckOutUpdateRequest entity) {
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

		entity.setDelayclosetime(entity.getDelayclosetime() * 60);

		new ScheduleCheckOutUpdateClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(entity))
				.execute();
		LogAction.getInstance().debug("send to blackcore success(sdk|ScheduleCheckOutUpdateClient).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is null.");
		}
		if (StringUtils.isBlank(entity.getStarttime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(start time).");
		}
		if (entity.getFunction() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(function).");
		}
		if (entity.getTemperature() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(temperature).");
		}
		if (entity.getDelayclosetime() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(delay close time).");
		}
	}
}
