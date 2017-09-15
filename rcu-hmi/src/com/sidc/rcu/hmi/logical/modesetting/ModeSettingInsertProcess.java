package com.sidc.rcu.hmi.logical.modesetting;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingInsertRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcumodesetting.RcuDeviceSettingInsertClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ModeSettingInsertProcess extends AbstractAPIProcess {

	private ModeSettingInsertRequest entity;

	public ModeSettingInsertProcess(final ModeSettingInsertRequest entity) {
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

		new RcuDeviceSettingInsertClient<Object>("http://10.60.1.39:7056/blackcore", json).execute();

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "entity is empty.");
		}

		if (StringUtils.isBlank(entity.getModeName())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "ModeName is empty.");
		}

		if (entity.getGroupId() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "GroupId is empty.");
		}

		if (entity.getMode().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Mode is empty.");
		}
	}
}
