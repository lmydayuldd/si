package com.sidc.blackcore.api.ra.rcumodesetting.request;

import java.io.Serializable;

public class RcuModeDeleteRequest implements Serializable {
	private static final long serialVersionUID = 153898868739698627L;
	private int modeid;

	public int getModeid() {
		return modeid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeleteRequest [modeid=");
		builder.append(modeid);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeDeleteRequest(int modeid) {
		super();
		this.modeid = modeid;
	}

}
