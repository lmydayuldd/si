package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;

public class RcuModeDeviceListBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6917470166161555434L;
	private int modelId;
	private int rcuDeviceId;
	private String device;

	public RcuModeDeviceListBean(int modelId, int rcuDeviceId, String device) {
		super();
		this.modelId = modelId;
		this.rcuDeviceId = rcuDeviceId;
		this.device = device;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeviceListBean [modelId=");
		builder.append(modelId);
		builder.append(", rcuDeviceId=");
		builder.append(rcuDeviceId);
		builder.append(", device=");
		builder.append(device);
		builder.append("]");
		return builder.toString();
	}

	public int getModelId() {
		return modelId;
	}

	public int getRcuDeviceId() {
		return rcuDeviceId;
	}

	public String getDevice() {
		return device;
	}
}
