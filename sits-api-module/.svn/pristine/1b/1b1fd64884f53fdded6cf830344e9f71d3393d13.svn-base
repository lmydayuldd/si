package com.sidc.blackcore.api.sits.token.bean;

import java.io.Serializable;

public class InfoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1544987895969330512L;
	private String deviceid;
	private int operatingsystem;
	private String version;
	private String pushtoken;

	public String getDeviceid() {
		return deviceid;
	}

	public int getOperatingsystem() {
		return operatingsystem;
	}

	public String getVersion() {
		return version;
	}

	public String getPushtoken() {
		return pushtoken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InfoBean [deviceid=");
		builder.append(deviceid);
		builder.append(", operatingsystem=");
		builder.append(operatingsystem);
		builder.append(", version=");
		builder.append(version);
		builder.append(", pushtoken=");
		builder.append(pushtoken);
		builder.append("]");
		return builder.toString();
	}

	public InfoBean(String deviceid, int operatingsystem, String version, String pushtoken) {
		super();
		this.deviceid = deviceid;
		this.operatingsystem = operatingsystem;
		this.version = version;
		this.pushtoken = pushtoken;
	}

}
