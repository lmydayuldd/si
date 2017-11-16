/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;
import java.util.List;

public class ModeInitialGroupBean implements Serializable {
	private static final long serialVersionUID = -1063992053784219870L;
	private int groupid;
	private List<ModeInitialDevicesBean> devices;

	public int getGroupid() {
		return groupid;
	}

	public List<ModeInitialDevicesBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialGroupBean [groupid=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public ModeInitialGroupBean(int groupid, List<ModeInitialDevicesBean> devices) {
		super();
		this.groupid = groupid;
		this.devices = devices;
	}

}
