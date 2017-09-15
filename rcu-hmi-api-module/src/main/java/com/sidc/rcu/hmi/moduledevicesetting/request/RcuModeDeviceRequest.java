package com.sidc.rcu.hmi.moduledevicesetting.request;

import java.io.Serializable;

public class RcuModeDeviceRequest implements Serializable {

	private static final long serialVersionUID = -3561649424412918161L;

	private int modeId;

	public int getModeId() {
		return modeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeviceRequest [modeId=");
		builder.append(modeId);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeDeviceRequest(int modeId) {
		super();
		this.modeId = modeId;
	}

}
