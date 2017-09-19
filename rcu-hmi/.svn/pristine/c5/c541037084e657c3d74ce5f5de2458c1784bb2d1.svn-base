package com.sidc.rcu.hmi.commander;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.bean.RcuBulbInfoBean;
import com.sidc.rcu.hmi.bean.RcuHvacInfoBean;
import com.sidc.rcu.hmi.bean.RcuRoomInfoBean;
import com.sidc.rcu.hmi.bean.RcuServiceInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.rcu.hmi.websocket.bean.RoomControlCatalogueWebsocketBean;
import com.sidc.rcu.hmi.websocket.bean.RoomControlDevicesWebsocketBean;
import com.sidc.rcu.hmi.websocket.bean.RoomControlWebsocketBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class RcuStatusProcess extends AbstractAPIProcess {

	private BlackcoreInitialBean blackcoreSetting;

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		this.blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance().get(CommonDataKey.BLACKCORE_SETTING);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final HashMap<String, RcuRoomInfoBean> roomMap = (HashMap<String, RcuRoomInfoBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_ROOM_INFO);

		final HashMap<String, List<RcuBulbInfoBean>> bulbMap = (HashMap<String, List<RcuBulbInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_BULB_INFO);

		final HashMap<String, List<RcuServiceInfoBean>> serviceMap = (HashMap<String, List<RcuServiceInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_SERVICE_INFO);

		final HashMap<String, List<RcuHvacInfoBean>> hvacMap = (HashMap<String, List<RcuHvacInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_HVAC_INFO);

		final List<RoomControlWebsocketBean> list = new ArrayList<RoomControlWebsocketBean>();

		for (final RcuRoomInfoBean entity : roomMap.values()) {

			final List<RoomControlCatalogueWebsocketBean> catalogueList = new ArrayList<RoomControlCatalogueWebsocketBean>();

			if (!bulbMap.get(entity.getRoomNo()).isEmpty()) {
				catalogueList.add(bulbProcess(bulbMap.get(entity.getRoomNo())));
			}

			if (!serviceMap.get(entity.getRoomNo()).isEmpty()) {
				catalogueList.add(serviceProcess(serviceMap.get(entity.getRoomNo())));
			}
			
			try{
				if (!hvacMap.get(entity.getRoomNo()).isEmpty()) {
					catalogueList.add(hvacProcess(hvacMap.get(entity.getRoomNo())));
				}
			}catch(Exception e){
				
			}
		

			list.add(new RoomControlWebsocketBean(entity.getRoomNo(), catalogueList));
		}

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {

		if (blackcoreSetting == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "blackcoreSetting is null(DataCenter).");
		}
	}

	private RoomControlCatalogueWebsocketBean bulbProcess(final List<RcuBulbInfoBean> bulbList) {
		if (bulbList.isEmpty()) {
			return null;
		}

		final List<RoomControlDevicesWebsocketBean> list = new ArrayList<RoomControlDevicesWebsocketBean>();

		for (final RcuBulbInfoBean bulbEntity : bulbList) {
			String status = "0";
			if (!StringUtils.isBlank(bulbEntity.getStatus())) {
				status = bulbEntity.getStatus();
			}
			list.add(new RoomControlDevicesWebsocketBean(bulbEntity.getKeycode(), status));
		}

		return new RoomControlCatalogueWebsocketBean("BULB", list);
	}

	private RoomControlCatalogueWebsocketBean serviceProcess(final List<RcuServiceInfoBean> serviceList) {

		if (serviceList.isEmpty()) {
			return null;
		}

		final List<RoomControlDevicesWebsocketBean> list = new ArrayList<RoomControlDevicesWebsocketBean>();

		for (final RcuServiceInfoBean serviceEntity : serviceList) {
			String status = "0";
			if (!StringUtils.isBlank(serviceEntity.getStatus())) {
				status = serviceEntity.getStatus();
			}
			list.add(new RoomControlDevicesWebsocketBean(serviceEntity.getKeycode(), status));
		}

		return new RoomControlCatalogueWebsocketBean("SERVICE", list);
	}

	private RoomControlCatalogueWebsocketBean hvacProcess(final List<RcuHvacInfoBean> hvacList) {
		if (hvacList.isEmpty()) {
			return null;
		}

		final List<RoomControlDevicesWebsocketBean> list = new ArrayList<RoomControlDevicesWebsocketBean>();

		final RcuHvacInfoBean hvacEntity = hvacList.get(0);

		String status = "0";
		if (!StringUtils.isBlank(hvacEntity.getStatus())) {
			status = hvacEntity.getStatus();
		}

		list.add(new RoomControlDevicesWebsocketBean(hvacEntity.getKeycode(), status, hvacEntity.getSpeed(),
				hvacEntity.getTemperature()));

		return new RoomControlCatalogueWebsocketBean("AIR-CONDITION", list);
	}
}
