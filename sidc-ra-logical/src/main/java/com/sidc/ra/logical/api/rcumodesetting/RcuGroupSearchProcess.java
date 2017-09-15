package com.sidc.ra.logical.api.rcumodesetting;

import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeSettingListRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupModelManager;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class RcuGroupSearchProcess extends AbstractAPIProcess {
	private RcuModeSettingListRequest entity;

	public RcuGroupSearchProcess(final RcuModeSettingListRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		LogAction.getInstance().debug("start process");

		int modelId = RcuGroupModelManager.getInstance().searchRcuModel(entity.getRcuGroupId(),
				entity.getRcuAgentBehaviorId());
		LogAction.getInstance().debug("step 1/2: get rcu_model_id success.(RcuGroupModelManager|searchRcuModel)");

		final String content = RcuModeManager.getInstance().searchRcuModel(modelId);
		LogAction.getInstance().debug("step 2/2: get content success.(RcuModelManager|searchRcuModel)");

		return content;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
	}
}
