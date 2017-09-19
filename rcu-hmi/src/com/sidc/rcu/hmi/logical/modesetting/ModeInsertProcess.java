package com.sidc.rcu.hmi.logical.modesetting;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeInsertRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcu.mode.RcuModeInsertClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ModeInsertProcess extends AbstractAPIProcess {

	private final ModeInsertRequest entity;

	public ModeInsertProcess(final ModeInsertRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("entity:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(entity);

		final String json = APIParser.getInstance().toJson(request);

		new RcuModeInsertClient<Object>(blackcoreSetting.getUrl(), json).execute();

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getModename())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(mode name).");
		}
		if (entity.getDevices() == null || entity.getDevices().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(devices).");
		}
	}
}
