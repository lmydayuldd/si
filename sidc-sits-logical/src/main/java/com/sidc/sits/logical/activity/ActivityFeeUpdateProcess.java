package com.sidc.sits.logical.activity;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeLangBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivityFeeUpdateRequest;
import com.sidc.dao.sits.manager.ActivityManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ActivityFeeUpdateProcess extends AbstractAuthAPIProcess {

	private final ActivityFeeUpdateRequest entity;
	private final String STEP = "1";

	public ActivityFeeUpdateProcess(final ActivityFeeUpdateRequest entity) {
		// TODO Auto-generated constructor stub
		super(entity.getToken());
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		ActivityManager.getInstance().updateFee(entity.getId(), entity.getStatus(), entity.getList());
		LogAction.getInstance().debug("step 1/" + STEP + " :update success(ActivityManager|updateFee).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of token.");
		}
		if (entity.getId() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of id.");
		}
		if (entity.getStatus() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of list.");
		}
		for (final ActivityFeeLangBean feeLangEntity : entity.getList()) {
			if (StringUtils.isBlank(feeLangEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name.");
			}
			if (StringUtils.isBlank(feeLangEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode.");
			}
			if (feeLangEntity.getName().length() > 50) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name(length).");
			}
			if (feeLangEntity.getLangcode().length() > 20) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode(lang code).");
			}
		}
		if (!ActivityManager.getInstance().isExistOfFeeID(entity.getId())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "fee id is not existed.");
		}
	}
}
