package com.sidc.blackcore.api.ra.rcumodesetting.bean;

import java.util.List;

public class RcuModeSettingModeBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8016485979249598032L;
	private String catalogue;
	private List<RcuModeSettingDevicesBean> devices;

	public RcuModeSettingModeBean(String catalogue, List<RcuModeSettingDevicesBean> devices) {
		super();
		this.catalogue = catalogue;
		this.devices = devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeSettingModeBean [catalogue=");
		builder.append(catalogue);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public String getCatalogue() {
		return catalogue;
	}

	public List<RcuModeSettingDevicesBean> getDevices() {
		return devices;
	}
}
