package com.sidc.rcu.hmi.groupdevice.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupDeviceInsertRequest implements Serializable {
	private static final long serialVersionUID = -1033309183428333692L;
	private int groupid;
	private List<Integer> devices = new ArrayList<Integer>();

	public int getGroupid() {
		return groupid;
	}

	public List<Integer> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupDeviceInsertRequest [groupId=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public GroupDeviceInsertRequest(int groupid, List<Integer> devices) {
		super();
		this.groupid = groupid;
		this.devices = devices;
	}

}
