package com.sidc.rcu.hmi.modulesetting.bean;

import java.io.Serializable;

public class ModuleDeviceListBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2602772729542417748L;
	private int groupId;
	private int deviceId;
	private String device;

	public int getGroupId() {
		return groupId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public String getDevice() {
		return device;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModuleDeviceListBean [groupId=");
		builder.append(groupId);
		builder.append(", deviceId=");
		builder.append(deviceId);
		builder.append(", device=");
		builder.append(device);
		builder.append("]");
		return builder.toString();
	}

	public ModuleDeviceListBean(int groupId, int deviceId, String device) {
		super();
		this.groupId = groupId;
		this.deviceId = deviceId;
		this.device = device;
	}

}
