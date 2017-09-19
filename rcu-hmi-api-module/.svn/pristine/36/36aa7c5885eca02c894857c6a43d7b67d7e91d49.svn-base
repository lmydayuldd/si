package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;

public class DeviceBean implements Serializable {
	private static final long serialVersionUID = -7417697473265494212L;
	private String keycode;
	private DeviceSettingBean data;

	public String getKeycode() {
		return keycode;
	}

	public DeviceSettingBean getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceBean [keycode=");
		builder.append(keycode);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

	public DeviceBean(String keycode, DeviceSettingBean data) {
		super();
		this.keycode = keycode;
		this.data = data;
	}

}
