package com.sidc.rcu.hmi.logical.rcugroup;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.group.request.GroupInsertRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcugroup.RcuGroupInsertClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class GroupInsertProcess extends AbstractAPIProcess {
	private final GroupInsertRequest entity;

	public GroupInsertProcess(final GroupInsertRequest entity) {
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

		final APIContentRequest apiRequest = new APIContentRequest(entity);

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final String result = (String) new RcuGroupInsertClient(blackcoreSetting.getUrl(),
				APIParser.getInstance().toJson(apiRequest)).execute();

		return result;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (StringUtils.isBlank(entity.getGroupname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group name)");
		}
		if (entity.getDevices() != null && entity.getDevices().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(devices)");
		}
		for (final int deviceId : entity.getDevices()) {
			if (deviceId <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(device id)");
			}
		}
	}
}
