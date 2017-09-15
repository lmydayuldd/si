package com.sidc.rcu.hmi.udp.receiver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.bean.RcuCardInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractUDPReceiver;
import com.sidc.rcu.hmi.receiver.bean.RcuServiceBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.RcuReceiverLog;
import com.sidc.utils.status.SystemStatus;

public class RcuCardReceiver extends AbstractUDPReceiver {
	private final String roomNo;
	private List<RcuServiceBean> list = new ArrayList<RcuServiceBean>();

	public RcuCardReceiver(final String roomNo, final List<RcuServiceBean> list) {
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
		HashMap<String, List<RcuCardInfoBean>> map = (HashMap<String, List<RcuCardInfoBean>>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_CARD_INFO);
		RcuReceiverLog.getInstance().debug("step:1/4 get DataCenter success.");

		if (map == null || map.isEmpty()) {
			RcuReceiverLog.getInstance().debug("step:1.1/4 DataCenter is null or empty(not find this roomno).");
			return null;
		}

		final List<RcuCardInfoBean> cardList = map.get(roomNo);

		if (cardList == null || cardList.isEmpty()) {
			RcuReceiverLog.getInstance().debug("step: 2.1/4 DataCenter not find this roomno.");
			return null;
		}

		for (RcuCardInfoBean cardEntity : cardList) {
			cardEntity.setAuthorization("0");

			if (cardEntity.getKeycode().equals("NOCARD")) {
				cardEntity.setStatus("1");
			} else {
				cardEntity.setStatus("0");
			}
		}

		for (RcuServiceBean entity : list) {
			for (RcuCardInfoBean cardEntity : cardList) {
				if (cardEntity.getKeycode().equals(entity.getKeycode())) {
					cardEntity.setAuthorization(entity.getAuthorization());

					if (cardEntity.getKeycode().equals("NOCARD")) {
						cardEntity.setStatus("1");
					} else {
						cardEntity.setStatus(entity.getStatus());
					}

					cardEntity.setTime(new Date());

					break;
				}
			}
		}
		RcuReceiverLog.getInstance().debug("step:2/4 status update success.");

		map.put(roomNo, cardList);
		RcuReceiverLog.getInstance().debug("step: 3/4 list put on to map success.");

		DataCenter.getInstance().put(CommonDataKey.RCU_CARD_INFO, map);
		RcuReceiverLog.getInstance().debug("step:4/4 put on to DataCenter success.");
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
