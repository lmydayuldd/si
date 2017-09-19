package com.sidc.rcu.hmi.groupdevice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupDeviceBean implements Serializable {
	private static final long serialVersionUID = -540137339223449588L;
	private int groupid;
	private String groupname;
	private List<DeviceInfoBean> devices = new ArrayList<DeviceInfoBean>();

	public int getGroupid() {
		return groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public List<DeviceInfoBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupDeviceBean [groupid=");
		builder.append(groupid);
		builder.append(", groupname=");
		builder.append(groupname);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public GroupDeviceBean(int groupid, String groupname, List<DeviceInfoBean> devices) {
		super();
		this.groupid = groupid;
		this.groupname = groupname;
		this.devices = devices;
	}

}
