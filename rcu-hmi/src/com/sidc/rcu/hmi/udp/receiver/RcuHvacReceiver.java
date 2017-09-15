package com.sidc.rcu.hmi.udp.receiver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.bean.RcuHvacInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractUDPReceiver;
import com.sidc.rcu.hmi.receiver.bean.RcuServiceBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.RcuReceiverLog;
import com.sidc.utils.status.SystemStatus;

public class RcuHvacReceiver extends AbstractUDPReceiver {

	private final String roomNo;
	private List<RcuServiceBean> list = new ArrayList<RcuServiceBean>();

	public RcuHvacReceiver(final String roomNo, final List<RcuServiceBean> list) {
		this.roomNo = roomNo;
		this.list = list;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		RcuReceiverLog.getInstance().debug("RcuHvacReceiver|roomNo:" + roomNo + " list:" + list);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		HashMap<String, List<RcuHvacInfoBean>> map = (HashMap<String, List<RcuHvacInfoBean>>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_HVAC_INFO);
		RcuReceiverLog.getInstance().debug("step: 1/5 get DataCenter success.");

		if (map == null || map.isEmpty()) {
			RcuReceiverLog.getInstance().debug("step: 1.1/5 DataCenter is null.");
			return null;
		}

		final List<RcuHvacInfoBean> hvacList = map.get(roomNo);
		RcuReceiverLog.getInstance().debug("step: 2/5 map convert to list success.");

		if (hvacList == null || hvacList.isEmpty()) {
			RcuReceiverLog.getInstance().debug("step: 2.1/5 DataCenter not find this roomno.");
			return null;
		}

		for (RcuServiceBean entity : list) {
			for (RcuHvacInfoBean hvacEntity : hvacList) {
				final String tempKeycode = "HVAC-" + entity.getPosition();
				if (hvacEntity.getKeycode().equals(tempKeycode)) {
					hvacEntity.setActualTemp(entity.getActualTemp());
					hvacEntity.setIsAirConditioning(entity.getIsAirConditioning());
					hvacEntity.setMode(entity.getMode());
					hvacEntity.setPosition(entity.getPosition());
					hvacEntity.setSpeed(entity.getSpeed());
					hvacEntity.setStatus(entity.getStatus());
					hvacEntity.setTemperature(entity.getTemperature());
					hvacEntity.setTime(new Date());
					break;
				}
			}
		}

		RcuReceiverLog.getInstance().debug("step: 3/5 list update success.");

		map.put(roomNo, hvacList);
		RcuReceiverLog.getInstance().debug("step: 4/5 list put on to map success.");

		DataCenter.getInstance().put(CommonDataKey.RCU_HVAC_INFO, map);
		RcuReceiverLog.getInstance().debug("step: 5/5 put on to DataCenter success.");
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(roomNo)) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "roomNo is null.");
		}
		if (list.isEmpty()) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "list is empty.");
		}
	}
}
