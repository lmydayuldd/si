package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.bean.LangBean;

public class GroupModeDeviceInfoBean implements Serializable {
	private static final long serialVersionUID = 1335863884740335480L;
	private int deviceid;
	private String device;
	private List<LangBean> langs = new ArrayList<LangBean>();

	public int getDeviceid() {
		return deviceid;
	}

	public String getDevice() {
		return device;
	}

	public List<LangBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupModeDeviceInfoBean [deviceid=");
		builder.append(deviceid);
		builder.append(", device=");
		builder.append(device);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public GroupModeDeviceInfoBean(int deviceid, String device, List<LangBean> langs) {
		super();
		this.deviceid = deviceid;
		this.device = device;
		this.langs = langs;
	}

}
