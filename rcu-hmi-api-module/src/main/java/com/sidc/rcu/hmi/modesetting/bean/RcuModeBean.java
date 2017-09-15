package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;
import java.util.List;

public class RcuModeBean implements Serializable {
	private static final long serialVersionUID = -4485652867967427519L;

	private List<RcuModeCatalogueBean> mode;

	public List<RcuModeCatalogueBean> getMode() {
		return mode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeBean [mode=");
		builder.append(mode);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeBean(List<RcuModeCatalogueBean> mode) {
		super();
		this.mode = mode;
	}

}
