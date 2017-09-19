package com.sidc.rcu.hmi.moduledevicesetting.request;

import java.io.Serializable;

public class RcuModeDeviceRequest implements Serializable {

	private static final long serialVersionUID = -3561649424412918161L;

	private int modeid;

	public int getModeid() {
		return modeid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeviceRequest [modeid=");
		builder.append(modeid);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeDeviceRequest(int modeid) {
		super();
		this.modeid = modeid;
	}

}
