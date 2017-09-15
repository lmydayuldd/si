/**
 * 
 */
package com.sidc.ra.logical.api.rcu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.request.DynamicRoomRequest;
import com.sidc.blackcore.api.ra.response.DynamicRcuInfoEntity;
import com.sidc.blackcore.api.ra.roominfo.bean.DynamicRcuDevicesEntity;
import com.sidc.blackcore.api.ra.roominfo.bean.DynamicRcuGroupEntity;
import com.sidc.blackcore.api.ra.roominfo.bean.DynamicRcuHvacEntity;
import com.sidc.blackcore.api.ra.roominfo.bean.RcuConditionEntity;
import com.sidc.blackcore.api.ra.roominfo.bean.RcuHvacEntity;
import com.sidc.configuration.common.key.CommonCatalogueRCUKey;
import com.sidc.dao.ra.response.DeviceCatalogue;
import com.sidc.dao.ra.response.DeviceEnity;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.ra.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class DynamicRoomRcuInfoProcess extends AbstractAuthAPIProcess {

	private DynamicRoomRequest enity;

	/**
	 * 
	 */
	public DynamicRoomRcuInfoProcess(DynamicRoomRequest enity, String ip) {
		super(enity.getRoomno(), ip);
		this.enity = enity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#init()
	 */
	@Override
	protected void init() throws SiDCException, Exception {

		LogAction.getInstance().debug("Request: " + enity);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#process()
	 */
	@Override
	protected Object process() throws SiDCException, Exception {

		Ignite ignite = Ignition.ignite();
		IgniteCache<String, RoomRcuEnity> roomRCUStatusCache = ignite.getOrCreateCache("RoomRCUStatus");

		Iterator<Entry<String, RoomRcuEnity>> it = roomRCUStatusCache.iterator();
		while (it.hasNext()) {
			Entry<String, RoomRcuEnity> entry = it.next();
			if (entry.getKey().equals(this.enity.getRoomno())) {

				List<DynamicRcuGroupEntity> list = outputFormat(entry.getValue());

				final DynamicRcuInfoEntity rcuInfo = new DynamicRcuInfoEntity(enity.getRoomno(), enity.getIp(), list);

				return rcuInfo;
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#check()
	 */
	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Rquest is illegal");
		}

		if (StringUtils.isBlank(this.enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "RoomNo is empty");
		}

		if (StringUtils.isBlank(this.enity.getIp())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "ip is empty");
		}
	}

	private List<DynamicRcuGroupEntity> outputFormat(final RoomRcuEnity entity) {
		Gson gson = new Gson();

		List<DynamicRcuGroupEntity> list = new ArrayList<DynamicRcuGroupEntity>();

		for (DeviceCatalogue deviceCatEntity : entity.getCatalogues()) {

			List<Serializable> serializable = new ArrayList<Serializable>();

			// 只有冷氣資料長的不一樣 要特別處理

			if (deviceCatEntity.getCatalogue().equals(CommonCatalogueRCUKey.AIR_CONDITION)) {
				for (DeviceEnity deviceEntity : deviceCatEntity.getDevices()) {

					if (deviceEntity.getCondition() == null) {
						LogAction.getInstance().debug(deviceEntity.getKeycode() + " Condition is null");
						continue;
					}

					final RcuHvacEntity hvacEntity = gson.fromJson(gson.toJson(deviceEntity.getCondition()),
							RcuHvacEntity.class);

					if (hvacEntity == null) {
						LogAction.getInstance().debug(
								deviceEntity.getKeycode() + "condition convert fail |" + deviceEntity.getCondition());
						continue;
					}

					final DynamicRcuHvacEntity rcuEntity = new DynamicRcuHvacEntity(deviceEntity.getKeycode(),
							hvacEntity.getMode(), hvacEntity.getStatus(), hvacEntity.getActualTemp(),
							hvacEntity.getTemperature());

					if (rcuEntity != null) {
						serializable.add(rcuEntity);
					}
				}
			} else {
				for (DeviceEnity deviceEntity : deviceCatEntity.getDevices()) {

					if (deviceEntity.getCondition() == null) {
						LogAction.getInstance().debug(deviceEntity.getKeycode() + " Condition is null");
						continue;
					}

					final RcuConditionEntity otherDeviceEntity = gson.fromJson(gson.toJson(deviceEntity.getCondition()),
							RcuConditionEntity.class);

					if (otherDeviceEntity == null) {
						LogAction.getInstance().debug(
								deviceEntity.getKeycode() + "condition convert fail |" + deviceEntity.getCondition());
						continue;
					}

					final DynamicRcuDevicesEntity rcuEntity = new DynamicRcuDevicesEntity(deviceEntity.getKeycode(),
							otherDeviceEntity.getStatus());

					if (rcuEntity != null) {
						serializable.add(rcuEntity);
					}
				}
			}

			list.add(new DynamicRcuGroupEntity(deviceCatEntity.getCatalogue(), serializable));
		}
		return list;
	}
}
