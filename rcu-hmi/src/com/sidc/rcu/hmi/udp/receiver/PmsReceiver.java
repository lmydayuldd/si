package com.sidc.rcu.hmi.udp.receiver;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.conf.PmsCommonKey;
import com.sidc.rcu.hmi.framework.abs.AbstractUDPReceiver;
import com.sidc.rcu.hmi.receiver.bean.RcuServiceBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.RcuReceiverLog;
import com.sidc.utils.status.SystemStatus;

public class PmsReceiver extends AbstractUDPReceiver {

	private final String roomNo;
	private List<RcuServiceBean> list = new ArrayList<RcuServiceBean>();

	public PmsReceiver(final String roomNo, final List<RcuServiceBean> list) {
		this.roomNo = roomNo;
		this.list = list;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		RcuReceiverLog.getInstance().debug("PmsReceiver|room no:" + roomNo + " list:" + list);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		for (final RcuServiceBean entity : list) {
			if (StringUtils.isBlank(entity.getKeycode())) {
				continue;
			}

			switch (entity.getKeycode()) {
			case PmsCommonKey.CHECKIN:
				roomStatusProcess(PmsCommonKey.CHECKIN, PmsCommonKey.CHECKOUT);
				break;
			case PmsCommonKey.CHECKOUT:
				roomStatusProcess(PmsCommonKey.CHECKOUT, PmsCommonKey.CHECKIN);
				break;
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private void roomStatusProcess(final String insertKey, final String deleteKey) {
		// 資料 放進去
		List<String> insertList = (List<String>) DataCenter.getInstance().get(insertKey);
		if (insertList == null) {
			insertList = new ArrayList<String>();
		}
		if (!insertList.contains(roomNo)) {
			insertList.add(roomNo);
		}

		DataCenter.getInstance().put(insertKey, insertList);

		// 移除 資料
		List<String> deleteList = (List<String>) DataCenter.getInstance().get(deleteKey);
		if (deleteList == null) {
			deleteList = new ArrayList<String>();
		}
		if (deleteList.contains(roomNo)) {
			deleteList.remove(roomNo);
		}

		DataCenter.getInstance().put(deleteKey, deleteList);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (list == null || list.isEmpty()) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "list is empty.");
		}
		if (StringUtils.isBlank(roomNo)) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "roomNo is null.");
		}
	}
}
