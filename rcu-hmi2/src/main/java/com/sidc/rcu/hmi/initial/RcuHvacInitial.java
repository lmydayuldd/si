package com.sidc.rcu.hmi.initial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.bean.LanguageBean;
import com.sidc.rcu.hmi.bean.RcuDeviceBean;
import com.sidc.rcu.hmi.bean.RcuHvacBean;
import com.sidc.rcu.hmi.bean.RcuHvacInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.SystemStatus;

public class RcuHvacInitial extends AbstractAPIProcess {
	private final static Logger logger = LoggerFactory.getLogger(RcuHvacInitial.class);

	private String roomNo;
	private List<RcuDeviceBean> list;
	private List<LanguageBean> langsList = new ArrayList<LanguageBean>();

	public RcuHvacInitial(String roonNo, List<RcuDeviceBean> list, List<LanguageBean> langsList) {
		this.roomNo = roonNo;
		this.list = list;
		this.langsList = langsList;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
		LogAction.getInstance().debug("room no=" + roomNo + " list=" + list + " langsList=" + langsList);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		HashMap<String, RcuHvacBean> map = (HashMap<String, RcuHvacBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_HVAC_INFO);

		LogAction.getInstance().debug("step 1/3 get data from DataCenter success");

		if (map == null || map.isEmpty()) {
			map = new HashMap<String, RcuHvacBean>();
			LogAction.getInstance().debug("step 1.1/3 DataCenter is null.");
		}

		List<RcuHvacInfoBean> hvacList = new ArrayList<RcuHvacInfoBean>();

		for (RcuDeviceBean entity : list) {
			if (entity.getCondition() == null) {
				LogAction.getInstance().debug("keycode:" + entity.getKeycode() + " condition is null.");
				continue;
			}

			hvacList.add(new RcuHvacInfoBean(roomNo, entity.getKeycode(), entity.getCondition().getStatus(), null, null,
					null, null, null, null, entity.getLangs()));
		}

		map.put(roomNo, new RcuHvacBean(roomNo, hvacList, langsList));
		LogAction.getInstance().debug("step 2/3 put on data success");

		DataCenter.getInstance().put(CommonDataKey.RCU_HVAC_INFO, map);
		LogAction.getInstance().debug("step 3/3 put to dataCenter success");
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(roomNo)) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "room no is null.");
		}
		if (list == null || list.isEmpty()) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "list is null.");
		}
		if (langsList == null || langsList.isEmpty()) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "langsList is null.");
		}
	}
}
