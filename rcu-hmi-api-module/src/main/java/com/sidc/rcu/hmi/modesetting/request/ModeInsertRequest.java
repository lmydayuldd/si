package com.sidc.rcu.hmi.modesetting.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.modesetting.bean.DeviceBean;

public class ModeInsertRequest implements Serializable {
	private static final long serialVersionUID = 3345457989025598378L;
	private int modeid;
	private int groupid;
	private List<DeviceBean> devices = new ArrayList<DeviceBean>();

	public int getModeid() {
		return modeid;
	}

	public int getGroupid() {
		return groupid;
	}

	public List<DeviceBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInsertRequest [modeid=");
		builder.append(modeid);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public ModeInsertRequest(int modeid, int groupid, List<DeviceBean> devices) {
		super();
		this.modeid = modeid;
		this.groupid = groupid;
		this.devices = devices;
	}

}
