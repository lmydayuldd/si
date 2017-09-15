package com.sidc.blackcore.api.ra.rcumodulesetting.request;

public class RcuModuleDeviceListRequest implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8285245442643225040L;
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModuleDeviceListRequest [groupId=");
		builder.append(groupId);
		builder.append("]");
		return builder.toString();
	}

	public RcuModuleDeviceListRequest(int groupId) {
		super();
		this.groupId = groupId;
	}

}
