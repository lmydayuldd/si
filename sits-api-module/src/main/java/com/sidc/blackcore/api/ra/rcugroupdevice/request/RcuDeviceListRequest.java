package com.sidc.blackcore.api.ra.rcugroupdevice.request;

import java.util.ArrayList;
import java.util.List;

public class RcuDeviceListRequest implements java.io.Serializable {

	public void setGroupList(List<RcuGroupNameEnity> groupList) {
		this.groupList = groupList;
	}

	public void setDeviceList(List<RcuDevicesNameEnity> deviceList) {
		this.deviceList = deviceList;
	}

	private static final long serialVersionUID = -4220267934812306791L;
	private List<RcuGroupNameEnity> groupList = new ArrayList<RcuGroupNameEnity>();
	private List<RcuDevicesNameEnity> deviceList = new ArrayList<RcuDevicesNameEnity>();

	public List<RcuGroupNameEnity> getGroupList() {
		return groupList;
	}

	public List<RcuDevicesNameEnity> getDeviceList() {
		return deviceList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceListRequest [groupList=");
		builder.append(groupList);
		builder.append(", deviceList=");
		builder.append(deviceList);
		builder.append("]");
		return builder.toString();
	}
}
