package com.sidc.rcu.hmi.modesetting.request;

import java.io.Serializable;

public class ModeSettingListRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1151414080614403675L;
	private int modeid;
	private int groupid;

	public int getModeid() {
		return modeid;
	}

	public int getGroupid() {
		return groupid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeSettingListRequest [modeid=");
		builder.append(modeid);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append("]");
		return builder.toString();
	}

	public ModeSettingListRequest(int modeid, int groupid) {
		super();
		this.modeid = modeid;
		this.groupid = groupid;
	}

}
