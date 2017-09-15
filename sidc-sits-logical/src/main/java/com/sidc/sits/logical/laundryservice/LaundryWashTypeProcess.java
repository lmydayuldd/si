package com.sidc.sits.logical.laundryservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryWashTypeRequest;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryWashTypeResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.LaundryServiceManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class LaundryWashTypeProcess extends AbstractAPIProcess {

	private final LaundryWashTypeRequest entity;
	private final String STEP = "1";

	public LaundryWashTypeProcess(final LaundryWashTypeRequest entity) {
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
		List<LaundryWashTypeResponse> list = new ArrayList<LaundryWashTypeResponse>();

		if (entity.getStatus().equals("all")) {
			if (entity.getLangcode().equals("all")) {
				list = LaundryServiceManager.getInstance().selectWashType();
			} else {
				list = LaundryServiceManager.getInstance().selectWashType(entity.getLangcode());
			}
			LogAction.getInstance().debug("step 1/" + STEP + " :select success(LaundryServiceManager|selectWashType).");
		} else {
			int status = -1;

			switch (entity.getStatus()) {
			case "enabled":
				status = 1;
				break;
			case "disable":
				status = 0;
				break;
			}

			if (entity.getLangcode().equals("all")) {
				list = LaundryServiceManager.getInstance().selectWashTypeByStatus(status);
			} else {
				list = LaundryServiceManager.getInstance().selectWashTypeByStatus(status, entity.getLangcode());
			}
			LogAction.getInstance()
					.debug("step 1/" + STEP + " :select success(LaundryServiceManager|selectWashTypeByStatus).");
		}
		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of lang code.");
		}
	}
}
