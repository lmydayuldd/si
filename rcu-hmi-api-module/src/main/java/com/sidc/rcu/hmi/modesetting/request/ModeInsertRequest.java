package com.sidc.rcu.hmi.modesetting.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModeInsertRequest implements Serializable {
	private static final long serialVersionUID = 6673406326271312265L;
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
		builder.append("ModeInsertRequest [modename=");
		builder.append(modename);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public ModeInsertRequest(String modename, List<Integer> devices) {
		super();
		this.modename = modename;
		this.devices = devices;
	}

}
