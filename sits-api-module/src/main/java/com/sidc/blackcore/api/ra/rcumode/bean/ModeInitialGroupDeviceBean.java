/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;

public class ModeInitialGroupDeviceBean implements Serializable {
	private static final long serialVersionUID = -1031707946271563912L;
	private int groupid;
	private List<RcuDeviceEnity> devices;

	public int getGroupid() {
		return groupid;
	}

	public List<RcuDeviceEnity> getDevices() {
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

	public ModeInitialGroupDeviceBean(int groupid, List<RcuDeviceEnity> devices) {
		super();
		this.groupid = groupid;
		this.devices = devices;
	}

}
