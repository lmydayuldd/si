package com.sidc.blackcore.api.mobile.message.request;

import java.io.Serializable;

public class GuestMessageHistoryInfoRequest implements Serializable {
	private static final long serialVersionUID = 8375112317615427877L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String deviceid;

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getDeviceid() {
		return deviceid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuestMessageHistoryInfoRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", deviceid=");
		builder.append(deviceid);
		builder.append("]");
		return builder.toString();
	}

	public GuestMessageHistoryInfoRequest(String publickey, String privatekey, String roomno, String deviceid) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.deviceid = deviceid;
	}

}
