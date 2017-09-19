package com.sidc.rcu.hmi.logical.rcucommand;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.rcucommand.request.HvacControlRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcu.command.RcuHvacControlClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class HvacControlProcess extends AbstractAPIProcess {
	private final HvacControlRequest entity;

	public HvacControlProcess(final HvacControlRequest entity) {
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
		LogAction.getInstance().debug("blackcore url:" + blackcoreSetting.getUrl());

		entity.setDelayclosetime(entity.getDelayclosetime() * 60);
		// new RcuHvacControlClient("http://10.60.1.39:7056/blackcore",
		// UDPParser.getInstance().toJsonByContent(entity)).execute();

		new RcuHvacControlClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(entity)).execute();
		LogAction.getInstance().debug("send to blackcore success.");
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is null.");
		}
		if (entity.getFunction() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(function).");
		}
		if (entity.getTemperature() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(temperature).");
		}
		if (StringUtils.isBlank(entity.getType())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(type).");
		}
		if (entity.getDelayclosetime() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(delay close time).");
		}
		if (entity.getType().equals("room") || entity.getType().equals("floor")) {
			if (entity.getItemlist().isEmpty()) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(item list).");
			}
		}
	}
}
