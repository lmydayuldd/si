package com.sidc.blackcore.api.ra.roominfo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DynamicRcuGroupEntity implements java.io.Serializable {
	private static final long serialVersionUID = -3391151266201699327L;
	private String catalogue;
	private List<Serializable> devices = new ArrayList<Serializable>();

	public String getCatalogue() {
		return catalogue;
	}

	public List<Serializable> getDevices() {
		return devices;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}

	public void setDevices(List<Serializable> devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DynamicRcuGroupEntity [catalogue=");
		builder.append(catalogue);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public DynamicRcuGroupEntity(String catalogue, List<Serializable> devices) {
		super();
		this.catalogue = catalogue;
		this.devices = devices;
	}

	public DynamicRcuGroupEntity() {
		super();
	}

}
