package com.sidc.rcu.hmi.initial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.bean.RcuBulbInfoBean;
import com.sidc.rcu.hmi.bean.RcuDeviceBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.SystemStatus;

public class RcuBulbInitial extends AbstractAPIProcess {
	private final static Logger logger = LoggerFactory.getLogger(RcuBulbInitial.class);

	private final String roomNo;
	private List<RcuDeviceBean> list = new ArrayList<RcuDeviceBean>();

	public RcuBulbInitial(final String roomNo, final List<RcuDeviceBean> list) {
		this.roomNo = roomNo;
		this.list = list;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
		LogAction.getInstance().debug("room no:" + roomNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		HashMap<String, List<RcuBulbInfoBean>> bulbMap = (HashMap<String, List<RcuBulbInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_BULB_INFO);
		LogAction.getInstance().debug("step 1/3 get data from DataCenter success");

		if (bulbMap == null || bulbMap.isEmpty()) {
			bulbMap = new HashMap<String, List<RcuBulbInfoBean>>();
			LogAction.getInstance().debug("step 1.1/3 DataCenter is null.");
		}

		List<RcuBulbInfoBean> bulbList = new ArrayList<RcuBulbInfoBean>();

		for (RcuDeviceBean entity : list) {
			if (entity.getKeycode() == null) {
				LogAction.getInstance().debug("keycode is null.");
				continue;
			}
			bulbList.add(new RcuBulbInfoBean(roomNo, entity.getKeycode(), entity.getLangs()));
		}

		bulbMap.put(roomNo, bulbList);
		LogAction.getInstance().debug("step 2/3 put on data success");

		DataCenter.getInstance().put(CommonDataKey.RCU_BULB_INFO, bulbMap);
		LogAction.getInstance().debug("step 3/3 put to dataCenter success");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(roomNo)) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "room no is null.");
		}
		if (list.isEmpty()) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "list is null.");
		}
	}
}
