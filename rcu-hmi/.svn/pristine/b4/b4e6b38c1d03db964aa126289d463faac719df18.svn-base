package com.sidc.rcu.hmi.logical.rcucommand;

import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.rcucommand.request.HvacCenterControlRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcu.command.RcuHvacCenterControlClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class HvacCenterControlProcess extends AbstractAPIProcess {
	private HvacCenterControlRequest entity;

	public HvacCenterControlProcess(final HvacCenterControlRequest entity) {
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
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		LogAction.getInstance().debug("process start");
		// new RcuHvacCenterControlClient(blackcoreSetting.getUrl(),
		// UDPParser.getInstance().toJsonByContent(entity));

		new RcuHvacCenterControlClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(entity))
				.execute();

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is null.");
		}
	}

}
