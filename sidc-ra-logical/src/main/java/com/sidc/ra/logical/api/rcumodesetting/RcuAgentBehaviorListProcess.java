package com.sidc.ra.logical.api.rcumodesetting;

import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuAgentBehaviorEntity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuAgentBehaviorManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Joe
 *
 */
public class RcuAgentBehaviorListProcess extends AbstractAPIProcess {

	public RcuAgentBehaviorListProcess() {
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		// LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		List<RcuAgentBehaviorEntity> list = RcuAgentBehaviorManager.getInstance().selectAll();
		LogAction.getInstance().debug("step 1/1: get id success.(RcuAgentBehaviorManager|selectAll)");
		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}
}
