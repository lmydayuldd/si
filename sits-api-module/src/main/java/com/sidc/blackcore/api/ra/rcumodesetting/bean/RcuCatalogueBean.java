package com.sidc.blackcore.api.ra.rcumodesetting.bean;

import java.util.ArrayList;
import java.util.List;

public class RcuCatalogueBean implements java.io.Serializable {
	private static final long serialVersionUID = 8094639756067419426L;
	private String catalogue;
	private List<RcuKeyCodeBean> devices = new ArrayList<RcuKeyCodeBean>();

	public String getCatalogue() {
		return catalogue;
	}

	public void setDevices(List<RcuKeyCodeBean> devices) {
		this.devices = devices;
	}

	public List<RcuKeyCodeBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuCatalogueBean [catalogue=");
		builder.append(catalogue);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public RcuCatalogueBean(String catalogue, List<RcuKeyCodeBean> devices) {
		super();
		this.catalogue = catalogue;
		this.devices = devices;
	}

}
