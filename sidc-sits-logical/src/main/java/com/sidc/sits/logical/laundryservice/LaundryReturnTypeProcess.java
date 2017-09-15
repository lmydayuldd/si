package com.sidc.sits.logical.laundryservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryReturnTypeRequest;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryReturnTypeResponse;
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
public class LaundryReturnTypeProcess extends AbstractAPIProcess {

	private final LaundryReturnTypeRequest entity;
	private final String STEP = "1";

	public LaundryReturnTypeProcess(final LaundryReturnTypeRequest entity) {
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
		List<LaundryReturnTypeResponse> list = new ArrayList<LaundryReturnTypeResponse>();

		if (entity.getStatus().equals("all")) {
			if (entity.getLangcode().equals("all")) {
				list = LaundryServiceManager.getInstance().selectReturnType();
			} else {
				list = LaundryServiceManager.getInstance().selectReturnType(entity.getLangcode());
			}
			LogAction.getInstance()
					.debug("step 1/" + STEP + " :select success(LaundryServiceManager|selectReturnType).");
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
				list = LaundryServiceManager.getInstance().selectReturnTypeByStatus(status);
			} else {
				list = LaundryServiceManager.getInstance().selectReturnTypeByStatus(status, entity.getLangcode());
			}
			LogAction.getInstance()
					.debug("step 1/" + STEP + " :select success(LaundryServiceManager|selectReturnTypeByStatus).");
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
