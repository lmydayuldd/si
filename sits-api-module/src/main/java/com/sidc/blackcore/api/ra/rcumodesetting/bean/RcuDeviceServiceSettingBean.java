package com.sidc.blackcore.api.ra.rcumodesetting.bean;

public class RcuDeviceServiceSettingBean implements java.io.Serializable {
	private static final long serialVersionUID = -909674166846713307L;
	private int status;

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceServiceSettingBean [status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public RcuDeviceServiceSettingBean(int status) {
		super();
		this.status = status;
	}

}
