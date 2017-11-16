package com.sidc.blackcore.api.sits.rcu.request;

import java.io.Serializable;

public class ServiceCommandRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4827159469986251763L;
	private String roomno;
	private String keycode;
	private String value;
	
	public ServiceCommandRequest(String roomno, String ip, String keycode, String value) {
		super();
		this.roomno = roomno;
		this.keycode = keycode;
		this.value = value;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getKeycode() {
		return keycode;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceCommandRequest [roomno=");
		builder.append(roomno);
		builder.append(", keycode=");
		builder.append(keycode);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
