/**
 * 
 */
package com.sidc.blackcore.api.mobile.message.request;

import java.io.Serializable;

public class DeviceCheckInStatusRequest implements Serializable {
	private static final long serialVersionUID = -6233246611511750422L;
	private String devices;

	public String getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceCheckInStatusRequest [devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public DeviceCheckInStatusRequest(String devices) {
		super();
		this.devices = devices;
	}

}
