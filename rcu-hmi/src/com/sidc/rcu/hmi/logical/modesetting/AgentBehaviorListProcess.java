package com.sidc.rcu.hmi.logical.modesetting;

import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.RcuAgentBehaviorBean;
import com.sidc.sdk.blackcore.rcumodesetting.RcuAgentBehaviorListClient;
import com.sidc.utils.exception.SiDCException;

public class AgentBehaviorListProcess extends AbstractAPIProcess {
	private List<RcuAgentBehaviorBean> list = new ArrayList<RcuAgentBehaviorBean>();

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		this.list = new RcuAgentBehaviorListClient("http://10.60.1.39:7056/blackcore").execute();
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {

		}
	}

}
