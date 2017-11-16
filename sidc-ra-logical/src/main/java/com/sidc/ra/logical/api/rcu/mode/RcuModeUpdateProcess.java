package com.sidc.ra.logical.api.rcu.mode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuCatalogueBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceBulbSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceHvacSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceServiceSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuGroupModuleBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuKeyCodeBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuModeBean;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuGroupModeUpdateRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RCUDeviceManager;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeUpdateProcess extends AbstractAPIProcess {
	private final RcuGroupModeUpdateRequest entity;
	private final String step = "";

	public RcuModeUpdateProcess(final RcuGroupModeUpdateRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// 所有的rcu 分類
		final List<String> rcugroups = RCUDeviceManager.getInstance().listRcuGroup();
		LogAction.getInstance().debug("step 1/" + step + ":list all rcu group.");

		// all device 對應到的分類
		final List<RcuGroupModuleBean> groupModules = RCUDeviceManager.getInstance().listRcuDeviceGroup();
		LogAction.getInstance().debug("step 2/" + step + ":list all device.");

		List<RcuCatalogueBean> catalogues = new ArrayList<RcuCatalogueBean>();

		final Gson gson = new Gson();

		// 已經判斷過的資料
		List<String> alreadyKeyCodes = new ArrayList<String>();

		// 從大方向開始整理
		for (final String group : rcugroups) {
			List<RcuKeyCodeBean> devices = new ArrayList<RcuKeyCodeBean>();
			for (final RcuGroupModuleBean moduleEntity : groupModules) {

				// 已經判斷過的keycode不再判斷,device類別 不屬於最外層迴圈的類別 不判斷
				if (alreadyKeyCodes.contains(moduleEntity.getDevice()) || !moduleEntity.getGroupname().equals(group)) {
					continue;
				}

				for (final RcuDeviceBean deviceEntity : entity.getDevices()) {
					// 找到相同的 device
					if (moduleEntity.getDevice().equals(deviceEntity.getKeycode())) {

						RcuKeyCodeBean keyCodeEntity = null;
						final String json = String.valueOf(deviceEntity.getData());

						switch (moduleEntity.getGroupname()) {
						case "BULB":
							keyCodeEntity = new RcuKeyCodeBean(deviceEntity.getKeycode(), bulbProcess(gson, json));
							break;
						case "AIR-CONDITION":
							keyCodeEntity = new RcuKeyCodeBean(deviceEntity.getKeycode(), hvacProcess(gson, json));
							break;
						case "SERVICE":
							keyCodeEntity = new RcuKeyCodeBean(deviceEntity.getKeycode(), serviceProcess(gson, json));
							break;
						default:
							keyCodeEntity = null;
							break;
						}
						alreadyKeyCodes.add(deviceEntity.getKeycode());
						devices.add(keyCodeEntity);
					}
				}
			}

			// 沒有資料就不用特別放入
			if (!devices.isEmpty()) {
				catalogues.add(new RcuCatalogueBean(group, devices));
			}
		}
		LogAction.getInstance().debug("step 3/" + step + ":format data to db.");

		RcuModeManager.getInstance().updateGroupMode(entity.getGroupid(), entity.getModeid(),
				gson.toJson(new RcuModeBean(catalogues)));
		LogAction.getInstance().debug("step 4/" + step + ":update success(RcuModeManager|insertGroupMode).");

		return null;
	}

	private RcuDeviceBulbSettingBean bulbProcess(final Gson gson, final String json) throws SiDCException {
		final RcuDeviceBulbSettingBean bulbEntity = gson.fromJson(json, RcuDeviceBulbSettingBean.class);
		if (bulbEntity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(bulb data)");
		}
		return bulbEntity;
	}

	private RcuDeviceServiceSettingBean serviceProcess(final Gson gson, final String json) throws SiDCException {
		final RcuDeviceServiceSettingBean serviceEntity = gson.fromJson(json, RcuDeviceServiceSettingBean.class);
		if (serviceEntity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(service data)");
		}
		return serviceEntity;
	}

	private RcuDeviceHvacSettingBean hvacProcess(final Gson gson, final String json) throws SiDCException {
		final RcuDeviceHvacSettingBean hvacEntity = gson.fromJson(json, RcuDeviceHvacSettingBean.class);
		if (hvacEntity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(service data)");
		}
		return hvacEntity;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getModeid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(mode id)");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id)");
		}
		if (entity.getDevices() == null && entity.getDevices().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(devices)");
		}
		for (final RcuDeviceBean deviceEntity : entity.getDevices()) {
			if (StringUtils.isBlank(deviceEntity.getKeycode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(key code)");
			}
			if (deviceEntity.getData() == null) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(data)");
			}
		}
	}
}
