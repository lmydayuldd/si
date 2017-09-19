package com.sidc.blackcore.api.ra.rcumodesetting.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RcuModeInsertRequest implements Serializable {
	private static final long serialVersionUID = 6041510443051330175L;
	private String modename;
	private List<Integer> devices = new ArrayList<Integer>();

	public String getModename() {
		return modename;
	}

	public List<Integer> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeInsertRequest [modename=");
		builder.append(modename);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeInsertRequest(String modename, List<Integer> devices) {
		super();
		this.modename = modename;
		this.devices = devices;
	}

}
