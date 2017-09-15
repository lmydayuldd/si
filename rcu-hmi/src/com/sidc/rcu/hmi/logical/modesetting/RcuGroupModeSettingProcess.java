package com.sidc.rcu.hmi.logical.modesetting;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeBean;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingListRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcu.mode.RcuGroupModeClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuGroupModeSettingProcess extends AbstractAPIProcess {
	private final ModeSettingListRequest entity;

	public RcuGroupModeSettingProcess(final ModeSettingListRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		LogAction.getInstance().debug("entity:" + entity);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(entity);

		final String json = APIParser.getInstance().toJson(request);

		final String result = (String) new RcuGroupModeClient(blackcoreSetting.getUrl(), json).execute();

		RcuModeBean test = (RcuModeBean) APIParser.getInstance().parses(result, RcuModeBean.class);

		return test;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal.");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id).");
		}
		if (entity.getModeid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(mode id).");
		}
	}
}
