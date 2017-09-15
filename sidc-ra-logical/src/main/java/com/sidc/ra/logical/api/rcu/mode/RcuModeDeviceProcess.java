package com.sidc.ra.logical.api.rcu.mode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuGroupModeRequest;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeSettingDeviceListResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeDeviceProcess extends AbstractAPIProcess {
	private RcuGroupModeRequest entity;

	public RcuModeDeviceProcess(final RcuGroupModeRequest entity) {
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

		final String content = RcuModeManager.getInstance().findContend(entity.getGroupid(), entity.getModeid());

		RcuModeSettingDeviceListResponse response = null;

		if (!StringUtils.isBlank(content)) {
			response = toResponseProcess(content);
		}

		return response;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getModeid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(model id)");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id)");
		}
	}

	private String formatStringProcess(final String str) {
		String strData = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			strData = m.replaceAll("");
		}
		return strData;
	}

	private RcuModeSettingDeviceListResponse toResponseProcess(final String content) {
		final Gson gson = new Gson();

		final RcuModeSettingDeviceListResponse enity = gson.fromJson(content, RcuModeSettingDeviceListResponse.class);

		return enity;
	}

}
