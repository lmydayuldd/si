package com.sidc.rcu.hmi.udp.receiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.bean.RcuCardInfoBean;
import com.sidc.rcu.hmi.bean.RcuDeviceBean;
import com.sidc.rcu.hmi.bean.RcuStatusBean;
import com.sidc.rcu.hmi.bean.receiver.RcuServiceBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractUDPReceiver;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.RcuReceiverLog;
import com.sidc.utils.status.SystemStatus;

public class RcuCardReceiver extends AbstractUDPReceiver {
	private String roomNo;
	private List<RcuServiceBean> list = new ArrayList<RcuServiceBean>();

	public RcuCardReceiver(String roomNo, List<RcuServiceBean> list) {
		this.roomNo = roomNo;
		this.list = list;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		RcuReceiverLog.getInstance().debug("RcuCardReceiver|room no=" + roomNo + " list=" + list);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		HashMap<String, RcuCardInfoBean> map = (HashMap<String, RcuCardInfoBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_CARD_INFO);
		RcuReceiverLog.getInstance().debug("step:1/3 get DataCenter success.");

		if (map == null || map.isEmpty()) {
			RcuReceiverLog.getInstance().debug("step:1.1/3 DataCenter is null or empty(not find this roomno).");
			return null;
		}

		RcuCardInfoBean cardInfoEntity = map.get(roomNo);

		if (cardInfoEntity == null) {
			RcuReceiverLog.getInstance().debug("step:1.2/3 not found data from DataCenter.");
			return null;
		}

		for (RcuServiceBean entity : list) {
			for (RcuDeviceBean dcviceEntity : cardInfoEntity.getList()) {
				if (dcviceEntity.getKeycode().equals(entity.getCardType())) {
					if (dcviceEntity.getCondition() == null) {
						dcviceEntity.setCondition(new RcuStatusBean(entity.getStatus(), entity.getCardType(), null,
								null, null, null, null, null, null));
						continue;
					}
					dcviceEntity.getCondition().setStatus(entity.getStatus());
				}
			}
		}
		RcuReceiverLog.getInstance().debug("step:2/3 status update success.");

		DataCenter.getInstance().put(CommonDataKey.RCU_CARD_INFO, map);
		RcuReceiverLog.getInstance().debug("step:3/3 put on to DataCenter success.");
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "list is empty.");
		}
		if (StringUtils.isBlank(roomNo)) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "roomNo is null.");
		}
	}
}
