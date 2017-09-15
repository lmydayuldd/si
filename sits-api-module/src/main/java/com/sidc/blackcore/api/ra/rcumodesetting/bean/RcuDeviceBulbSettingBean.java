package com.sidc.blackcore.api.ra.rcumodesetting.bean;

public class RcuDeviceBulbSettingBean implements java.io.Serializable {
	private static final long serialVersionUID = -6450113751236729532L;
	private int status;

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceBulbSettingBean [status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public RcuDeviceBulbSettingBean(int status) {
		super();
		this.status = status;
	}

}
