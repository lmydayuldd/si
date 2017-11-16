package com.sidc.rcu.hmi.modedevicesetting.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.modeledevicesetting.bean.RcuDeviceEnity;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeDeviceListBean;

public class ModeDeviceSettingListResponse implements Serializable {
	private static final long serialVersionUID = 2422725116648656059L;
	private List<RcuDeviceEnity> allDeviceList = new ArrayList<RcuDeviceEnity>();
	private List<RcuModeDeviceListBean> modeDeviceList = new ArrayList<RcuModeDeviceListBean>();

	public List<RcuDeviceEnity> getAllDeviceList() {
		return allDeviceList;
	}

	public List<RcuModeDeviceListBean> getModeDeviceList() {
		return modeDeviceList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeDeviceSettingListResponse [allDeviceList=");
		builder.append(allDeviceList);
		builder.append(", modeDeviceList=");
		builder.append(modeDeviceList);
		builder.append("]");
		return builder.toString();
	}

	public ModeDeviceSettingListResponse(List<RcuDeviceEnity> allDeviceList,
			List<RcuModeDeviceListBean> modeDeviceList) {
		super();
		this.allDeviceList = allDeviceList;
		this.modeDeviceList = modeDeviceList;
	}

}
