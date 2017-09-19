package com.sidc.rcu.hmi.logical.rcugroup;

import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.groupdevice.bean.RcuGroupDeviceInsertBean;
import com.sidc.rcu.hmi.groupdevice.request.GroupDeviceInsertRequest;
import com.sidc.rcu.hmi.groupdevice.request.RcuGroupDeviceInsertRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcugroup.RcuGroupDeviceInsertClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class GroupDeviceInsertProcess extends AbstractAPIProcess {
	private final GroupDeviceInsertRequest entity;

	public GroupDeviceInsertProcess(final GroupDeviceInsertRequest entity) {
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

		// 發給 blackcore
		final List<RcuGroupDeviceInsertBean> groups = new ArrayList<RcuGroupDeviceInsertBean>();
		groups.add(new RcuGroupDeviceInsertBean(entity.getGroupid(), entity.getDevices()));

		final RcuGroupDeviceInsertRequest request = new RcuGroupDeviceInsertRequest(groups);

		final APIContentRequest apiRequest = new APIContentRequest(request);

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final String result = (String) new RcuGroupDeviceInsertClient(blackcoreSetting.getUrl(),
				APIParser.getInstance().toJson(apiRequest)).execute();

		return result;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id)");
		}
		if (entity.getDevices().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(devices)");
		}
		for (final int deviceId : entity.getDevices()) {
			if (deviceId <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(device id)");
			}
		}
	}
}
