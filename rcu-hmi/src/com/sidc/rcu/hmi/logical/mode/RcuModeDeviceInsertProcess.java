package com.sidc.rcu.hmi.logical.mode;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeDeviceInsertBean;
import com.sidc.rcu.hmi.moduledevicesetting.request.RcuModeDeviceInsertRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcumodesetting.RcuModeDeviceInsertClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeDeviceInsertProcess extends AbstractAPIProcess {
	private RcuModeDeviceInsertRequest entity;

	public RcuModeDeviceInsertProcess(final RcuModeDeviceInsertRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		LogAction.getInstance().debug("Entity:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(entity);

		final String json = APIParser.getInstance().toJson(request);

		return new RcuModeDeviceInsertClient<Object>(blackcoreSetting.getUrl(), json).execute();
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request illegal.");
		}

		if (entity.getModeId() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request illegal(modeId).");
		}

		if (entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request illegal(device list).");
		}

		for (RcuModeDeviceInsertBean deviceEntity : entity.getList()) {
			if (deviceEntity.getDeviceId() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request illegal(deviceId).");
			}
		}
	}
}
