package com.sidc.blackcore.api.ra.rcugroupdevice.bean;

public class RcuGroupInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = -1884816311125927228L;
	private int groupid;
	private String catalogue;

	public int getGroupid() {
		return groupid;
	}

	public String getCatalogue() {
		return catalogue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupInfoBean [groupid=");
		builder.append(groupid);
		builder.append(", catalogue=");
		builder.append(catalogue);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupInfoBean(int groupid, String catalogue) {
		super();
		this.groupid = groupid;
		this.catalogue = catalogue;
	}

}
