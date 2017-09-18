package com.sidc.rcu.hmi.logical.modesetting;

import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeDeviceListBean;
import com.sidc.rcu.hmi.moduledevicesetting.request.RcuModeDeviceRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcumodesetting.RcuModeDeviceListClient;
import com.sidc.utils.exception.SiDCException;

public class RcuModeDeviceProcess extends AbstractAPIProcess {
	private RcuModeDeviceRequest entity;
	private List<RcuModeDeviceListBean> list = new ArrayList<RcuModeDeviceListBean>();

	public RcuModeDeviceProcess(RcuModeDeviceRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(entity);

		this.list = new RcuModeDeviceListClient(blackcoreSetting.getUrl(), APIParser.getInstance().toJson(request))
				.execute();
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

}
