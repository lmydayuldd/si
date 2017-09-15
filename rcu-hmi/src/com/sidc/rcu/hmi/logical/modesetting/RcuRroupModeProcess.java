package com.sidc.rcu.hmi.logical.modesetting;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.DeviceBean;
import com.sidc.rcu.hmi.modesetting.request.ModeInsertRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcu.mode.RcuGroupModeInsertClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuRroupModeProcess extends AbstractAPIProcess {
	private final ModeInsertRequest entity;

	public RcuRroupModeProcess(final ModeInsertRequest entity) {
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

		new RcuGroupModeInsertClient(blackcoreSetting.getUrl(), json).execute();

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(group id).");
		}
		if (entity.getModeid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(mode id).");
		}
		if (entity.getDevices() == null || entity.getDevices().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(devices).");
		}
		for (final DeviceBean deviceEntity : entity.getDevices()) {
			if (StringUtils.isBlank(deviceEntity.getKeycode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(key code).");
			}
			if (deviceEntity.getData() == null) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(data).");
			}
		}
	}

}
