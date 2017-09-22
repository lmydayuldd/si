package com.sidc.rcu.hmi.logical.mode;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeDeleteRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcu.mode.RcuModeDeleteClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeDeleteProcess extends AbstractAPIProcess {
	private final ModeDeleteRequest entity;

	public RcuModeDeleteProcess(final ModeDeleteRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(entity);

		final String json = APIParser.getInstance().toJson(request);

		new RcuModeDeleteClient(blackcoreSetting.getUrl(), json).execute();

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getModeid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(mode id).");
		}
	}

}
