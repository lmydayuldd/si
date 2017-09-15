package com.sidc.blackcore.api.ra.rcumodesetting.bean;

import java.io.Serializable;

public class RcuDeviceBean implements Serializable {
	private static final long serialVersionUID = -7761660171853909041L;
	private String keycode;
	private Object data;

	public String getKeycode() {
		return keycode;
	}

	public Object getData() {
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

	public RcuDeviceBean(String keycode, Object data) {
		super();
		this.keycode = keycode;
		this.data = data;
	}

}
