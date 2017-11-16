/**
 * 
 */
package com.sidc.ra.logical.api.rcu.mode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialDevicesBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialDevicesInsertBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialGroupBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialGroupInsertBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialInsertBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialModuleBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeLangBean;
import com.sidc.blackcore.api.ra.rcumode.request.ModeInitialRequest;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuCatalogueBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceBulbSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceHvacSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceServiceSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuKeyCodeBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuModeBean;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeInitialProcess extends AbstractAPIProcess {

	private final ModeInitialRequest entity;
	private final String step = "2";

	public RcuModeInitialProcess(final ModeInitialRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		Gson gson = new Gson();
		List<ModeInitialInsertBean> modes = new ArrayList<ModeInitialInsertBean>();

		for (final ModeInitialBean modeEntity : entity.getModes()) {

			// 把名稱先取出來在處理
			final List<ModeInitialGroupInsertBean> list = RcuModeManager.getInstance()
					.modeInitialDeviceInfo(modeEntity.getGroups());

			List<ModeInitialModuleBean> modules = new ArrayList<ModeInitialModuleBean>();

			for (final ModeInitialGroupInsertBean insertEntity : list) {
				List<RcuCatalogueBean> catalogues = new ArrayList<RcuCatalogueBean>();
				List<Integer> devices = new ArrayList<Integer>();

				for (final ModeInitialDevicesInsertBean deviceEntity : insertEntity.getDevices()) {
					RcuKeyCodeBean keyCodeEntity = null;

					switch (deviceEntity.getGouprname()) {
					case "BULB":
						keyCodeEntity = new RcuKeyCodeBean(deviceEntity.getKeycode(), bulbProcess(deviceEntity));
						break;
					case "AIR-CONDITION":
						keyCodeEntity = new RcuKeyCodeBean(deviceEntity.getKeycode(), hvacProcess(deviceEntity));
						break;
					case "SERVICE":
						keyCodeEntity = new RcuKeyCodeBean(deviceEntity.getKeycode(), serviceProcess(deviceEntity));
						break;
					default:
						keyCodeEntity = null;
						break;
					}
					devices.add(deviceEntity.getId());
					catalogues = categorydataProcess(catalogues, deviceEntity.getGouprname(), keyCodeEntity);
				}

				if (!catalogues.isEmpty()) {
					modules.add(new ModeInitialModuleBean(insertEntity.getGroupid(),
							gson.toJson(new RcuModeBean(catalogues)), devices));
				}
			}

			modes.add(new ModeInitialInsertBean(modeEntity.getId(), modeEntity.getKeyname(), 1, modeEntity.getTimer(),
					modules, modeEntity.getLangs()));
		}
		LogAction.getInstance().debug("step 1/" + step + ":format data success " + gson.toJson(modes));

		RcuModeManager.getInstance().groupModeInitial(modes);
		LogAction.getInstance().debug("step 2/" + step + ":insert success.");

		return null;
	}

	private List<RcuCatalogueBean> categorydataProcess(final List<RcuCatalogueBean> catalogues,
			final String categoryName, final RcuKeyCodeBean keyCodeEntity) {
		boolean isPass = false;
		for (RcuCatalogueBean category : catalogues) {
			if (category.getCatalogue().equals(categoryName)) {
				isPass = true;
				break;
			}
		}

		if (!isPass) {
			List<RcuKeyCodeBean> devices = new ArrayList<RcuKeyCodeBean>();
			devices.add(keyCodeEntity);
			catalogues.add(new RcuCatalogueBean(categoryName, devices));
			return catalogues;
		}

		for (RcuCatalogueBean category : catalogues) {
			if (category.getCatalogue().equals(categoryName)) {
				List<RcuKeyCodeBean> devices = category.getDevices();
				devices.add(keyCodeEntity);
				category.setDevices(devices);
			}
		}
		return catalogues;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getModes() == null || entity.getModes().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(modes).");
		}

		List<Integer> groups = new ArrayList<Integer>();
		List<String> keycodes = new ArrayList<String>();

		for (final ModeInitialBean modeEntity : entity.getModes()) {
			if (modeEntity.getId() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(id).");
			}
			if (StringUtils.isBlank(modeEntity.getKeyname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(key name).");
			}
			if (modeEntity.getGroups() == null || modeEntity.getGroups().isEmpty()) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(groups).");
			}
			if (modeEntity.getLangs() == null || modeEntity.getLangs().isEmpty()) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langs).");
			}

			for (final ModeLangBean langEntity : modeEntity.getLangs()) {
				if (StringUtils.isBlank(langEntity.getLang())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(lang).");
				}
				if (StringUtils.isBlank(langEntity.getName())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(name).");
				}
				if (langEntity.getLang().length() > 5) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(lang length).");
				}
				if (langEntity.getName().length() > 50) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(name length).");
				}
				if (StringUtils.isBlank(langEntity.getDescription())) {
					if (langEntity.getDescription().length() > 1024) {
						throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(description).");
					}
				}
			}

			for (final ModeInitialGroupBean groupEntity : modeEntity.getGroups()) {
				if (groupEntity.getGroupid() <= 0) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(group id).");
				}
				for (final ModeInitialDevicesBean deviceEntity : groupEntity.getDevices()) {
					if (StringUtils.isBlank(deviceEntity.getKeycode())) {
						throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(key code).");
					}
					if (!keycodes.contains(deviceEntity.getKeycode())) {
						keycodes.add(deviceEntity.getKeycode());
					}
				}
				if (!groups.contains(groupEntity.getGroupid())) {
					groups.add(groupEntity.getGroupid());
				}
			}
		}
		RcuModeManager.getInstance().modeInitialCheck(groups, keycodes);
	}

	// 轉換成command格式
	private RcuDeviceServiceSettingBean serviceProcess(final ModeInitialDevicesInsertBean deviceEntity)
			throws SiDCException {
		return new RcuDeviceServiceSettingBean(deviceEntity.getStatus());
	}

	private RcuDeviceBulbSettingBean bulbProcess(final ModeInitialDevicesInsertBean deviceEntity) throws SiDCException {
		return new RcuDeviceBulbSettingBean(deviceEntity.getStatus());
	}

	private RcuDeviceHvacSettingBean hvacProcess(final ModeInitialDevicesInsertBean deviceEntity) throws SiDCException {
		return new RcuDeviceHvacSettingBean(deviceEntity.isPower(), deviceEntity.getFunction(),
				deviceEntity.getTemperature(), deviceEntity.getTimer(), deviceEntity.getSpeed());
	}

}
