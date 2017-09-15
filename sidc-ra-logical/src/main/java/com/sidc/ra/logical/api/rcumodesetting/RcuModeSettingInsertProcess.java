package com.sidc.ra.logical.api.rcumodesetting;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeSettingInsertRequest;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeSettingDeviceListResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeSettingInsertProcess extends AbstractAPIProcess {
	private RcuModeSettingInsertRequest entity;

	public RcuModeSettingInsertProcess(final RcuModeSettingInsertRequest entity) {
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
		// RcuModeSettingDeviceListResponse
		final Gson gson = new Gson();

		final RcuModeSettingDeviceListResponse enity = (RcuModeSettingDeviceListResponse) gson
				.fromJson(gson.toJson(entity), RcuModeSettingDeviceListResponse.class);

//		try{
//			RcuModeManager.getInstance().insertModeSetting(entity.getModeName(), entity.getGroupId(), gson.toJson(enity));
//		}catch(SQLException e){
//			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "data illegal");
//		}
		
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}

		if (entity.getGroupId() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(GroupId)");
		}

		if (StringUtils.isBlank(entity.getModeName())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(ModeName)");
		}

		if (entity.getMode().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(Mode)");
		}
	}
}
