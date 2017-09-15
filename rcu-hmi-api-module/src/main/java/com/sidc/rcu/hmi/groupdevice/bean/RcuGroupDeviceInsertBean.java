package com.sidc.rcu.hmi.groupdevice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RcuGroupDeviceInsertBean implements Serializable {
	private static final long serialVersionUID = -7484015803082752172L;
	private int groupid;
	private List<Integer> devices = new ArrayList<Integer>();

	public RcuGroupDeviceInsertBean(int groupid, List<Integer> devices) {
		super();
		this.groupid = groupid;
		this.devices = devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupDeviceInsertBean [groupid=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public int getGroupid() {
		return groupid;
	}

	public List<Integer> getDevices() {
		return devices;
	}
}
