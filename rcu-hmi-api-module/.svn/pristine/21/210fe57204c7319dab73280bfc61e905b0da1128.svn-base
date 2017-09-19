package com.sidc.rcu.hmi.group.bean;

import java.io.Serializable;

public class RcuGroupInsertBean implements Serializable {
	private static final long serialVersionUID = -8694326989499509506L;
	private int deviceid;
	private String device;
	private String devicename;

	public int getDeviceid() {
		return deviceid;
	}

	public String getDevice() {
		return device;
	}

	public String getDevicename() {
		return devicename;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceInfoBean [deviceid=");
		builder.append(deviceid);
		builder.append(", device=");
		builder.append(device);
		builder.append(", devicename=");
		builder.append(devicename);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupInsertBean(int deviceid, String device, String devicename) {
		super();
		this.deviceid = deviceid;
		this.device = device;
		this.devicename = devicename;
	}

}
