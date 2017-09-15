package com.sidc.blackcore.api.ra.rcugroupdevice.request;

public class RcuDevicesNameEnity implements java.io.Serializable {
	private static final long serialVersionUID = 3539823898976398562L;
	private String devicesName;

	public String getDevicesName() {
		return devicesName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDevicesNameEnity [devicesName=");
		builder.append(devicesName);
		builder.append("]");
		return builder.toString();
	}

	public RcuDevicesNameEnity(String devicesName) {
		super();
		this.devicesName = devicesName;
	}

}
