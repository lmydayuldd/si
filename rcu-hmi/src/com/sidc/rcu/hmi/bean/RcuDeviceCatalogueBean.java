package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.List;

public class RcuDeviceCatalogueBean implements Serializable {
	private static final long serialVersionUID = -431533695490931052L;
	private String catalogue;
	private List<RcuDeviceBean> devices;
	private List<LanguageBean> langs;

	public String getCatalogue() {
		return catalogue;
	}

	public List<RcuDeviceBean> getDevices() {
		return devices;
	}

	public List<LanguageBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceCatalogueBean [catalogue=");
		builder.append(catalogue);
		builder.append(", devices=");
		builder.append(devices);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public RcuDeviceCatalogueBean(String catalogue, List<RcuDeviceBean> devices, List<LanguageBean> langs) {
		super();
		this.catalogue = catalogue;
		this.devices = devices;
		this.langs = langs;
	}

}
