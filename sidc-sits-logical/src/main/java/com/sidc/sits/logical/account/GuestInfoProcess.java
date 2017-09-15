package com.sidc.sits.logical.account;

import java.util.List;

import com.sidc.blackcore.api.mobile.guest.bean.GuestInfoBean;
import com.sidc.blackcore.api.mobile.guest.request.GuestInfoRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.GuestManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class GuestInfoProcess extends AbstractAPIProcess {
	private final GuestInfoRequest entity;
	private final String STEP = "1";

	public GuestInfoProcess(final GuestInfoRequest entity) {
		// TODO Auto-generated constructor stub
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

		List<GuestInfoBean> list = GuestManager.getInstance().selectGuestInfo(entity.getRoomno());

		LogAction.getInstance().debug("step 1/" + STEP + ": get bean success(StaffManager|getStaffInfo).");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		// if (StringUtils.isBlank(entity.getToken())) {
		// throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request
		// illegal(token).");
		// }

	}
}
