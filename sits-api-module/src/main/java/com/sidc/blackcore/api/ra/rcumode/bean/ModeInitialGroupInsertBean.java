/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;
import java.util.List;

public class ModeInitialGroupInsertBean implements Serializable {
	private static final long serialVersionUID = -1031707946271563912L;
	private int groupid;
	private List<ModeInitialDevicesInsertBean> devices;

	public int getGroupid() {
		return groupid;
	}

	public List<ModeInitialDevicesInsertBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialGroupInsertBean [groupid=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public ModeInitialGroupInsertBean(int groupid, List<ModeInitialDevicesInsertBean> devices) {
		super();
		this.groupid = groupid;
		this.devices = devices;
	}

}
