package com.sidc.blackcore.api.ra.rcugroupdevice.bean;

import java.util.ArrayList;
import java.util.List;

public class RcuGroupDeviceInsertBean implements java.io.Serializable {
	private static final long serialVersionUID = 322835072576237220L;
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
		builder.append("RcuGroupDeviceInsertBean [groupid=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupDeviceInsertBean(int groupid, List<Integer> devices) {
		super();
		this.groupid = groupid;
		this.devices = devices;
	}

}
