package com.sidc.rcu.hmi.logical.modesetting;

import java.util.List;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeBean;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeCatalogueBean;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeDevicesBean;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingListRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcumodesetting.RcuDeviceListWithModeClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ModeSettingListProcess extends AbstractAPIProcess {
	/**
	 * function 1冷,2熱,3風 temperature 16~30 speed 0自動 1高 2中 3低 timer 分鐘
	 */
	private ModeSettingListRequest entity;

	public ModeSettingListProcess(final ModeSettingListRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("entity:" + entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(entity);

		final String json = APIParser.getInstance().toJson(request);

		final String response = new RcuDeviceListWithModeClient(blackcoreSetting.getUrl(), json).execute();

		final RcuModeBean modeEntity = (RcuModeBean) APIParser.getInstance().parses(response, RcuModeBean.class);

		modeProcess(modeEntity);

		return modeEntity.getMode();
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "entity is empty.");
		}
	}

	private void modeProcess(final RcuModeBean modeEntity) {
		for (RcuModeCatalogueBean entity : modeEntity.getMode()) {
			if (entity.getCatalogue().equals("AIR-CONDITION")) {
				hvacProcess(entity.getDevices());
			}
		}
	}

	private void hvacProcess(final List<RcuModeDevicesBean> list) {
		for (RcuModeDevicesBean entity : list) {
			if (entity.getKeycode().equals("HVAC-ALL")) {

			}
		}
	}
}
