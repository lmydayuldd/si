package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.bean.LangBean;

public class GroupModeInfoBean implements Serializable {
	private static final long serialVersionUID = 3567790233859224688L;
	private int groupid;
	private String catalogue;
	private List<LangBean> langs = new ArrayList<LangBean>();
	private List<GroupModeDeviceInfoBean> devices = new ArrayList<GroupModeDeviceInfoBean>();

	public int getGroupid() {
		return groupid;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public List<LangBean> getLangs() {
		return langs;
	}

	public List<GroupModeDeviceInfoBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupModeInfoBean [groupid=");
		builder.append(groupid);
		builder.append(", catalogue=");
		builder.append(catalogue);
		builder.append(", langs=");
		builder.append(langs);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public GroupModeInfoBean(int groupid, String catalogue, List<LangBean> langs,
			List<GroupModeDeviceInfoBean> devices) {
		super();
		this.groupid = groupid;
		this.catalogue = catalogue;
		this.langs = langs;
		this.devices = devices;
	}

}
