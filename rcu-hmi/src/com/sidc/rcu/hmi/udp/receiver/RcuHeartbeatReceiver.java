package com.sidc.rcu.hmi.udp.receiver;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.bean.RcuRoomStatusBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractUDPReceiver;
import com.sidc.rcu.hmi.receiver.bean.UdpRreceiveBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.SystemStatus;

/**
 * @author Joe
 *
 */

// 接收UDP 心跳訊息 有傳過來代表RUC活者
public class RcuHeartbeatReceiver extends AbstractUDPReceiver {
	private final static Logger logger = LoggerFactory.getLogger(RcuHeartbeatReceiver.class);

	private UdpRreceiveBean entity;

	public RcuHeartbeatReceiver(UdpRreceiveBean entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
		// LogAction.getInstance().debug("Entity:" + entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		HashMap<String, RcuRoomStatusBean> roomMap = (HashMap<String, RcuRoomStatusBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_ROOM_STATUS);

		if (roomMap == null || roomMap.isEmpty()) {
			roomMap = new HashMap<String, RcuRoomStatusBean>();
		}

		roomMap.put(entity.getRoomNo(), new RcuRoomStatusBean(entity.getRoomNo()));
		DataCenter.getInstance().put(CommonDataKey.RCU_ROOM_STATUS, roomMap);

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "entity is null.");
		}
	}
}
