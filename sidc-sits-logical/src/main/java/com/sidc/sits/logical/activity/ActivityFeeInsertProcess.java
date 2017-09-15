package com.sidc.sits.logical.activity;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeLangBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivityFeeInsertRequest;
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
public class ActivityFeeInsertProcess extends AbstractAuthAPIProcess {

	private final ActivityFeeInsertRequest entity;
	private final String STEP = "1";

	public ActivityFeeInsertProcess(final ActivityFeeInsertRequest entity) {
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

		final int id = ActivityManager.getInstance().insertFee(entity.getStatus(), entity.getList());
		LogAction.getInstance().debug("step 1/" + STEP + " :insert success(ActivityManager|insertFee).");

		return id;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
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
			if (feeLangEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode(lang code).");
			}
		}
	}
}
