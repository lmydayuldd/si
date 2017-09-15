package com.sidc.rcu.hmi.groupdevice.request;

import java.io.Serializable;

public class GroupDeviceRequest implements Serializable {
	private static final long serialVersionUID = 1009285685166642625L;
	private int groupid;

	public int getGroupid() {
		return groupid;
	}

	public GroupDeviceRequest(int groupid) {
		super();
		this.groupid = groupid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupDeviceRequest [groupid=");
		builder.append(groupid);
		builder.append("]");
		return builder.toString();
	}

}
