package com.sidc.blackcore.api.ra.rcumodesetting.request;

public class RcuModeDeviceInsertListEntity implements java.io.Serializable {

	private static final long serialVersionUID = -4382740817919649415L;
	private int deviceId;

	public int getDeviceId() {
		return deviceId;
	}

	public RcuModeDeviceInsertListEntity(int deviceId) {
		super();
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeviceInsertListEntity [deviceId=");
		builder.append(deviceId);
		builder.append("]");
		return builder.toString();
	}

}
