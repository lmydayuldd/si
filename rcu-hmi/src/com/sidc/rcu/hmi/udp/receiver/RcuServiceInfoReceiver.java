package com.sidc.rcu.hmi.udp.receiver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.bean.RcuServiceInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractUDPReceiver;
import com.sidc.rcu.hmi.receiver.bean.RcuServiceBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.RcuReceiverLog;
import com.sidc.utils.status.SystemStatus;

public class RcuServiceInfoReceiver extends AbstractUDPReceiver {

	private final String roomNo;
	private List<RcuServiceBean> list = new ArrayList<RcuServiceBean>();

	public RcuServiceInfoReceiver(final String roomNo, final List<RcuServiceBean> list) {
		this.roomNo = roomNo;
		this.list = list;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		RcuReceiverLog.getInstance()
				.debug("RcuServiceInfoReceive|roomNo:" + roomNo + " list:" + UDPParser.getInstance().toJson(list));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		HashMap<String, List<RcuServiceInfoBean>> map = (HashMap<String, List<RcuServiceInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_SERVICE_INFO);
		RcuReceiverLog.getInstance().debug("step: 1/5 get DataCenter success.");

		if (map == null || map.isEmpty()) {
			RcuReceiverLog.getInstance().debug("step: 1.1/5 DataCenter is null.");
			return null;
		}

		final List<RcuServiceInfoBean> serviceList = map.get(roomNo);
		RcuReceiverLog.getInstance().debug("step: 2/5 map convert to list success.");

		if (serviceList == null || serviceList.isEmpty()) {
			RcuReceiverLog.getInstance().debug("step: 2.1/5 DataCenter not find this roomno.");
			return null;
		}
		RcuReceiverLog.getInstance().debug("RcuServiceBean|roomNo:" + UDPParser.getInstance().toJson(serviceList));

		for (RcuServiceBean entity : list) {
			for (RcuServiceInfoBean serviceEntity : serviceList) {
				if (entity.getKeycode().equals(serviceEntity.getKeycode())) {
					serviceEntity.setStatus(entity.getStatus());
					serviceEntity.setTime(new Date());
					break;
				}
			}
		}
		RcuReceiverLog.getInstance().debug("step: 3/5 list update success.");

		map.put(roomNo, serviceList);
		RcuReceiverLog.getInstance().debug("step: 4/5 list put on to map success.");

		DataCenter.getInstance().put(CommonDataKey.RCU_SERVICE_INFO, map);
		RcuReceiverLog.getInstance().debug("step: 5/5 put on to DataCenter success.");
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
