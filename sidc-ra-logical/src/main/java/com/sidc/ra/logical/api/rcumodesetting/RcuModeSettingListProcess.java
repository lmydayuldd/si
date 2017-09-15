package com.sidc.ra.logical.api.rcumodesetting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeSettingListRequest;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeSettingDeviceListResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupModelManager;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 * 
 *         以房型、模式(checkin、checkout) 取得該模式下的devices狀態
 *
 */
public class RcuModeSettingListProcess extends AbstractAPIProcess {
	private RcuModeSettingListRequest entity;

	public RcuModeSettingListProcess(final RcuModeSettingListRequest entity) {
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

		int modelId = RcuGroupModelManager.getInstance().searchRcuModel(entity.getRcuGroupId(),
				entity.getRcuAgentBehaviorId());
		LogAction.getInstance().debug("step 1/2: get rcu_model_id success.(RcuGroupModelManager|searchRcuModel)");

		modelId = 1;

		final String content = RcuModeManager.getInstance().searchRcuModel(modelId);
		LogAction.getInstance().debug("step 2/2: get content success.(RcuModelManager|searchRcuModel)");

		final String fomatStr = formatStringProcess(content);
		return toResponseProcess(fomatStr);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
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
