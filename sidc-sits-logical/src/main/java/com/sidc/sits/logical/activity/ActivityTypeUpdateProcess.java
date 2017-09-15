package com.sidc.sits.logical.activity;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityTypeBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivityTypeUpdateRequest;
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
public class ActivityTypeUpdateProcess extends AbstractAuthAPIProcess {

	private final ActivityTypeUpdateRequest entity;
	private final String STEP = "1";

	public ActivityTypeUpdateProcess(final ActivityTypeUpdateRequest entity) {
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

		ActivityManager.getInstance().updateType(entity.getId(), entity.getStatus(), entity.getList());
		LogAction.getInstance().debug("step 1/" + STEP + " :update success(ActivityManager|updateType).");

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
		for (final ActivityTypeBean typeLangEntity : entity.getList()) {
			if (StringUtils.isBlank(typeLangEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name.");
			}
			if (StringUtils.isBlank(typeLangEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode.");
			}
			if (typeLangEntity.getName().length() > 50) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name(length).");
			}
			if (typeLangEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode(length).");
			}
			if (!StringUtils.isBlank(typeLangEntity.getDescription())) {
				if (typeLangEntity.getDescription().length() > 1024) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of description(length).");
				}
			}
		}
		if (!ActivityManager.getInstance().isExistOfTypeID(entity.getId())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "id is not existed.");
		}
	}
}
