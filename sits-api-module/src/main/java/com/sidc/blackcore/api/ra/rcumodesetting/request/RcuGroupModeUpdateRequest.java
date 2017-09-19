package com.sidc.blackcore.api.ra.rcumodesetting.request;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceBean;

public class RcuGroupModeUpdateRequest implements java.io.Serializable {
	private static final long serialVersionUID = -7655522904262771050L;
	private int modeid;
	private int groupid;
	private List<RcuDeviceBean> devices = new ArrayList<RcuDeviceBean>();

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
		builder.append("RcuGroupModeUpdateRequest [modeid=");
		builder.append(modeid);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupModeUpdateRequest(int modeid, int groupid, List<RcuDeviceBean> devices) {
		super();
		this.modeid = modeid;
		this.groupid = groupid;
		this.devices = devices;
	}

}
