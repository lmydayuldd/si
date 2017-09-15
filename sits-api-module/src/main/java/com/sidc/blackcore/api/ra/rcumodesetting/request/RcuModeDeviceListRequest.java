package com.sidc.blackcore.api.ra.rcumodesetting.request;

public class RcuModeDeviceListRequest implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5231309578434820123L;
	private int modeId;

	public RcuModeDeviceListRequest(int modeId) {
		super();
		this.modeId = modeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeDeviceRequest [modeId=");
		builder.append(modeId);
		builder.append("]");
		return builder.toString();
	}

	public int getModeId() {
		return modeId;
	}

}
