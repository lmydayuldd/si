/**
 * 
 */
package com.sidc.ra.logical.api.rcugroup;

import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupDeleteRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuGroupDeleteProcess extends AbstractAPIProcess {

	private final RcuGroupDeleteRequest entity;

	public RcuGroupDeleteProcess(final RcuGroupDeleteRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		RcuGroupManager.getInstance().deleteGroup(entity.getGroupid());

		return null;
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
	}
}
