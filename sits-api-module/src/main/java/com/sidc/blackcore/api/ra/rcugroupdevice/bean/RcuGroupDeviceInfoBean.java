package com.sidc.blackcore.api.ra.rcugroupdevice.bean;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;

public class RcuGroupDeviceInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = 138685552199659503L;
	private int deviceid;
	private String device;
	private List<LangsBean> langs = new ArrayList<LangsBean>();

	public int getDeviceid() {
		return deviceid;
	}

	public String getDevice() {
		return device;
	}

	public List<LangsBean> getLangs() {
		return langs;
	}

	public void setLangs(List<LangsBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupDeviceInfoBean [deviceid=");
		builder.append(deviceid);
		builder.append(", device=");
		builder.append(device);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupDeviceInfoBean(int deviceid, String device) {
		super();
		this.deviceid = deviceid;
		this.device = device;
	}

	public RcuGroupDeviceInfoBean(int deviceid, String device, List<LangsBean> langs) {
		super();
		this.deviceid = deviceid;
		this.device = device;
		this.langs = langs;
	}

}
