package com.sidc.blackcore.api.ra.rcugroupdevice.bean;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;

public class RcuGroupDeviceBean implements java.io.Serializable {
	private static final long serialVersionUID = -3844373415964521471L;
	private int groupid;
	private String catalogue;
	private List<LangsBean> langs = new ArrayList<LangsBean>();
	private List<RcuGroupDeviceInfoBean> devices = new ArrayList<RcuGroupDeviceInfoBean>();

	public int getGroupid() {
		return groupid;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public List<LangsBean> getLangs() {
		return langs;
	}

	public List<RcuGroupDeviceInfoBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupDeviceBean [groupid=");
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

	public RcuGroupDeviceBean(int groupid, String catalogue, List<LangsBean> langs,
			List<RcuGroupDeviceInfoBean> devices) {
		super();
		this.groupid = groupid;
		this.catalogue = catalogue;
		this.langs = langs;
		this.devices = devices;
	}

}
