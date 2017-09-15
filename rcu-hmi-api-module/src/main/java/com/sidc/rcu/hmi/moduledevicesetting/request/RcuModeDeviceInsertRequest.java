package com.sidc.rcu.hmi.moduledevicesetting.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.modesetting.bean.RcuModeDeviceInsertBean;

public class RcuModeDeviceInsertRequest implements Serializable {
	private static final long serialVersionUID = 5610799606439746052L;
	private int modeId;
	private List<RcuModeDeviceInsertBean> list = new ArrayList<RcuModeDeviceInsertBean>();

	public int getModeId() {
		return modeId;
	}

	public List<RcuModeDeviceInsertBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeviceInsertRequest [modeId=");
		builder.append(modeId);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeDeviceInsertRequest(int modeId, List<RcuModeDeviceInsertBean> list) {
		super();
		this.modeId = modeId;
		this.list = list;
	}

}
