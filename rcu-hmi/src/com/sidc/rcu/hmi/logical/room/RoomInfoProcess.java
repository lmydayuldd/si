package com.sidc.rcu.hmi.logical.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.bean.RcuCardInfoBean;
import com.sidc.rcu.hmi.bean.RcuHvacInfoBean;
import com.sidc.rcu.hmi.bean.RcuRoomInfoBean;
import com.sidc.rcu.hmi.bean.RcuRoomStatusBean;
import com.sidc.rcu.hmi.bean.RcuServiceInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.room.request.RcuRoomInfoRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.RoomRCUInfoClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.SystemStatus;

public class RoomInfoProcess extends AbstractAPIProcess {

	private List<RcuRoomInfoRequest> list = new ArrayList<RcuRoomInfoRequest>();

	@SuppressWarnings("unchecked")
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final String request = APIParser.getInstance()
				.toJson(new RoomRCUInfoClient(blackcoreSetting.getUrl()).execute());

		this.list = APIParser.getInstance().parse(request, new TypeToken<List<RcuRoomInfoRequest>>() {
		}.getType());

		LogAction.getInstance().debug("list:" + list);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		LogAction.getInstance().debug("process start");

		final HashMap<String, List<RcuServiceInfoBean>> serviceMap = (HashMap<String, List<RcuServiceInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_SERVICE_INFO);

		final HashMap<String, List<RcuHvacInfoBean>> hvacMap = (HashMap<String, List<RcuHvacInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_HVAC_INFO);

		final HashMap<String, List<RcuCardInfoBean>> cardMap = (HashMap<String, List<RcuCardInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_CARD_INFO);

		final HashMap<String, RcuRoomStatusBean> rcuMap = (HashMap<String, RcuRoomStatusBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_ROOM_STATUS);

		final HashMap<String, RcuRoomInfoBean> map = (HashMap<String, RcuRoomInfoBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_ROOM_INFO);

		if (cardMap == null) {
			LogAction.getInstance().debug("cardMap is null.");
		}

		LogAction.getInstance().debug("step 1/: get DataCenter success");

		List<RcuRoomInfoBean> roomList = new ArrayList<RcuRoomInfoBean>();

		for (RcuRoomInfoRequest entity : list) {
			final RcuRoomInfoBean infoEntity = map.get(entity.getRoomno());

			if (infoEntity == null) {
				LogAction.getInstance().debug("step 1.1/:RcuRoomInfoBean is null " + entity.getRoomno());
				continue;
			}
			if (cardMap != null) {
				for (RcuCardInfoBean cardEntity : cardMap.get(entity.getRoomno())) {
					// 一個房間只會同時存在一張卡
					if (StringUtils.isBlank(cardEntity.getStatus())) {
						continue;
					}

					if (cardEntity.getKeycode().equals("NOCARD")) {
						if (cardEntity.getStatus().equals("1")) {
							infoEntity.setCard(null);
							break;
						}
					}

					if (cardEntity.getStatus().equals("1")) {
						infoEntity.setCard(cardEntity);
						break;
					}
				}
			}

			infoEntity.setService(serviceProcess(serviceMap, entity.getRoomno()));
			infoEntity.setHvac(hvadProcess(hvacMap, entity.getRoomno()));
			infoEntity.setRcu(rcuProcess(rcuMap, entity.getRoomno()));
			infoEntity.setCheckin(entity.isCheckin());
			infoEntity.setType(entity.getRcugroup());
			infoEntity.setFloor(entity.getFloor());
			roomList.add(infoEntity);
		}
		return APIParser.getInstance().toJson(roomList);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
			throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "request is null.");
		}
	}

	private List<RcuServiceInfoBean> serviceProcess(final HashMap<String, List<RcuServiceInfoBean>> serviceMap,
			final String roomNo) {
		// 服務 目前頁面只要SOS的服務 2016/12/21
		List<RcuServiceInfoBean> serviceList = new ArrayList<RcuServiceInfoBean>();

		if (serviceMap == null) {
			LogAction.getInstance().debug("serviceProcess:serviceMap is null.");
			return serviceList;
		}

		for (final RcuServiceInfoBean serviceEntity : serviceMap.get(roomNo)) {
			if (StringUtils.isBlank(serviceEntity.getStatus())) {
				continue;
			}
			if (serviceEntity.getStatus().equals("1")) {
				serviceList.add(serviceEntity);
			}
//			if (serviceEntity.getKeycode().equals("SOS")) {
//				if (StringUtils.isBlank(serviceEntity.getStatus())) {
//					continue;
//				}
//				if (serviceEntity.getStatus().equals("1")) {
//					serviceList.add(serviceEntity);
//				}
//			}
		}
		return serviceList;
	}

	private List<RcuHvacInfoBean> hvadProcess(final HashMap<String, List<RcuHvacInfoBean>> hvacMap,
			final String roomNo) {

		List<RcuHvacInfoBean> hvacList = new ArrayList<RcuHvacInfoBean>();

		if (hvacMap == null) {
			LogAction.getInstance().debug("hvadProcess:hvacMap is null.");
			return hvacList;
		}

		for (RcuHvacInfoBean hvacEntity : hvacMap.get(roomNo)) {
			if (StringUtils.isBlank(hvacEntity.getStatus())) {
				continue;
			}
			// 狀態是開的才處裡
			if (hvacEntity.getStatus().equals("1")) {// 0:close 1:open
				hvacList.add(hvacEntity);
			}
		}
		return hvacList;
	}

	private RcuRoomStatusBean rcuProcess(final HashMap<String, RcuRoomStatusBean> rcuMap, final String roomNo) {
		if (rcuMap == null || rcuMap.isEmpty()) {
			return null;
		}
		return rcuMap.get(roomNo);
	}
}
