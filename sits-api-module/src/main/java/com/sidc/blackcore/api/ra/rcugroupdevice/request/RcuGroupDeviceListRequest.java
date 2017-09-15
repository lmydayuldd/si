package com.sidc.blackcore.api.ra.rcugroupdevice.request;

public class RcuGroupDeviceListRequest implements java.io.Serializable {
	private static final long serialVersionUID = -899099701659176590L;
	private int groupid;

	public int getGroupid() {
		return groupid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupDeviceListRequest [groupid=");
		builder.append(groupid);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupDeviceListRequest(int groupid) {
		super();
		this.groupid = groupid;
	}

}
