package com.sidc.blackcore.api.ra.rcumodesetting.request;

import java.util.ArrayList;
import java.util.List;

public class RcuModeDeviceInsertRequest implements java.io.Serializable {

	private static final long serialVersionUID = -2593622299808447478L;

	private int modeId;
	private List<RcuModeDeviceInsertListEntity> list = new ArrayList<RcuModeDeviceInsertListEntity>();

	public int getModeId() {
		return modeId;
	}

	public List<RcuModeDeviceInsertListEntity> getList() {
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

	public RcuModeDeviceInsertRequest(int modeId, List<RcuModeDeviceInsertListEntity> list) {
		super();
		this.modeId = modeId;
		this.list = list;
	}

}
