package com.sidc.rcu.hmi.moduledevicesetting.request;

import java.io.Serializable;

public class ModuleDeviceRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6925970943219231847L;
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("moduleDeviceRequest [groupId=");
		builder.append(groupId);
		builder.append("]");
		return builder.toString();
	}

	public ModuleDeviceRequest(int groupId) {
		super();
		this.groupId = groupId;
	}

}
