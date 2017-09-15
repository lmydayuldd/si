package com.sidc.rcu.hmi.groupdevice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.bean.LangBean;

public class GroupDeviceResultBean implements Serializable {
	private static final long serialVersionUID = -7546406257559094371L;
	private int groupid;
	private List<LangBean> langs = new ArrayList<LangBean>();
	private List<DeviceInfoResultBean> devices = new ArrayList<DeviceInfoResultBean>();

	public int getGroupid() {
		return groupid;
	}

	public List<LangBean> getLangs() {
		return langs;
	}

	public List<DeviceInfoResultBean> getDevices() {
		return devices;
	}

	public GroupDeviceResultBean(int groupid, List<LangBean> langs, List<DeviceInfoResultBean> devices) {
		super();
		this.groupid = groupid;
		this.langs = langs;
		this.devices = devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupDeviceResultBean [groupid=");
		builder.append(groupid);
		builder.append(", langs=");
		builder.append(langs);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

}
