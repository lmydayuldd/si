package com.sidc.rcu.hmi.logical.modesetting;

import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.ModeBean;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcumodesetting.RcuModeListClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class RcuModeListProcess extends AbstractAPIProcess {
	List<ModeBean> entity = new ArrayList<ModeBean>();

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		this.entity = new RcuModeListClient("http://10.60.1.39:7056/blackcore").execute();
		LogAction.getInstance().debug("entity:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null || entity.isEmpty()) {
			// throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "entity is
			// empty.");
		}
	}
}
