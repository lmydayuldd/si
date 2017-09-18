package com.sidc.rcu.hmi.initial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.bean.LanguageBean;
import com.sidc.rcu.hmi.bean.RcuCardInfoBean;
import com.sidc.rcu.hmi.bean.RcuDeviceBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.SystemStatus;

public class RcuCardInitial extends AbstractAPIProcess {
	private final static Logger logger = LoggerFactory.getLogger(RcuCardInitial.class);
	private String roomNo;
	private List<RcuDeviceBean> list = new ArrayList<RcuDeviceBean>();
	private List<LanguageBean> langsList = new ArrayList<LanguageBean>();

	public RcuCardInitial(String roomNo, List<RcuDeviceBean> list, List<LanguageBean> langsList) {
		this.roomNo = roomNo;
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

		HashMap<String, RcuCardInfoBean> map = (HashMap<String, RcuCardInfoBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_CARD_INFO);
		LogAction.getInstance().debug("step 1/3 get data from DataCenter success");

		if (map == null || map.isEmpty()) {
			map = new HashMap<String, RcuCardInfoBean>();
			LogAction.getInstance().debug("step 1.1/3 DataCenter is null.");
		}
		map.put(roomNo, new RcuCardInfoBean(roomNo, list, langsList));
		LogAction.getInstance().debug("step 2/3 put on data success");

		DataCenter.getInstance().put(CommonDataKey.RCU_CARD_INFO, map);
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
