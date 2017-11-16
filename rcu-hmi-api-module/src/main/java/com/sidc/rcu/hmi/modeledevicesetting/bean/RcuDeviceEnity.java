package com.sidc.rcu.hmi.modeledevicesetting.bean;

import java.io.Serializable;

public class RcuDeviceEnity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -999061430748452222L;
	private int id;
	private String device;
	private String gouprName;

	public int getId() {
		return id;
	}

	public String getDevice() {
		return device;
	}

	public String getGouprName() {
		return gouprName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceEnity [id=");
		builder.append(id);
		builder.append(", device=");
		builder.append(device);
		builder.append(", gouprName=");
		builder.append(gouprName);
		builder.append("]");
		return builder.toString();
	}

	public RcuDeviceEnity(int id, String device, String gouprName) {
		super();
		this.id = id;
		this.device = device;
		this.gouprName = gouprName;
	}

}
