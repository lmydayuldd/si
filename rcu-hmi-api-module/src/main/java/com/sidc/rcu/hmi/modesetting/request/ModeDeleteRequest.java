package com.sidc.rcu.hmi.modesetting.request;

import java.io.Serializable;

public class ModeDeleteRequest implements Serializable {
	private static final long serialVersionUID = -3326524026373845491L;
	private int modeid;

	public int getModeid() {
		return modeid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeDeleteRequest [modeid=");
		builder.append(modeid);
		builder.append("]");
		return builder.toString();
	}

	public ModeDeleteRequest(int modeid) {
		super();
		this.modeid = modeid;
	}

}
