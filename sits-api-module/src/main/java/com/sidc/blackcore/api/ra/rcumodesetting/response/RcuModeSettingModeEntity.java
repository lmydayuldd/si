package com.sidc.blackcore.api.ra.rcumodesetting.response;

import java.util.List;

public class RcuModeSettingModeEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1467067972350258634L;
	private String catalogue;
	private List<RcuModeSettingDevicesEntity> devices;

	public String getCatalogue() {
		return catalogue;
	}

	public List<RcuModeSettingDevicesEntity> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeSettingModeEntity [catalogue=");
		builder.append(catalogue);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeSettingModeEntity(String catalogue, List<RcuModeSettingDevicesEntity> devices) {
		super();
		this.catalogue = catalogue;
		this.devices = devices;
	}
}
