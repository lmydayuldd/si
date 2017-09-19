package com.sidc.blackcore.api.ra.rcumodesetting.request;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceBean;

public class RcuGroupModeInsertRequest implements java.io.Serializable {
	private static final long serialVersionUID = -1238378758198767433L;
	private String modename;
	private int modeid;
	private int groupid;
	private List<RcuDeviceBean> devices = new ArrayList<RcuDeviceBean>();
	public String getModename() {
		return modename;
	}
	public int getModeid() {
		return modeid;
	}
	public int getGroupid() {
		return groupid;
	}
	public List<RcuDeviceBean> getDevices() {
		return devices;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupModeInsertRequest [modename=");
		builder.append(modename);
		builder.append(", modeid=");
		builder.append(modeid);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}
	public RcuGroupModeInsertRequest(String modename, int modeid, int groupid, List<RcuDeviceBean> devices) {
		super();
		this.modename = modename;
		this.modeid = modeid;
		this.groupid = groupid;
		this.devices = devices;
	}

}
