package com.sidc.sits.logical.laundryservice;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;
import com.sidc.blackcore.api.mobile.laundry.request.LaundryReturnTypeInsertRequest;
import com.sidc.dao.sits.manager.LaundryServiceManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class LaundryReturnTypeInsertProcess extends AbstractAuthAPIProcess {

	private final LaundryReturnTypeInsertRequest entity;
	private final String STEP = "1";

	public LaundryReturnTypeInsertProcess(final LaundryReturnTypeInsertRequest entity) {
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

		final int id = LaundryServiceManager.getInstance().insertReturnType(entity.getStatus(), entity.getList());
		LogAction.getInstance().debug("step 1/" + STEP + " :insert success(LaundryServiceManager|insertReturnType).");

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
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of list.");
		}
		for (final LaundryLangBean langEntity : entity.getList()) {
			if (StringUtils.isBlank(langEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name.");
			}
			if (StringUtils.isBlank(langEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode.");
			}
			if (langEntity.getName().length() > 25) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name(length).");
			}
			if (langEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode(lang code).");
			}
			if (!StringUtils.isBlank(langEntity.getDescription()) && langEntity.getDescription().length() > 1024) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode(description).");
			}
		}
	}
}
