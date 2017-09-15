package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;

public class RcuModeDeviceInsertBean implements Serializable {
	private static final long serialVersionUID = 3581201637157555055L;

	public RcuModeDeviceInsertBean(int deviceId) {
		super();
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeviceInsertBean [deviceId=");
		builder.append(deviceId);
		builder.append("]");
		return builder.toString();
	}

	public int getDeviceId() {
		return deviceId;
	}

	private int deviceId;
}
