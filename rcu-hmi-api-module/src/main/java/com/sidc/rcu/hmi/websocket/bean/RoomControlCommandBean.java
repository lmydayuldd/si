package com.sidc.rcu.hmi.websocket.bean;

import java.io.Serializable;

public class RoomControlCommandBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3898487706163034503L;

	private String uuid;
	private String roomno;
	private String keycode;
	private Object data;

	public RoomControlCommandBean(String uuid, String roomno, String keycode) {
		super();
		this.uuid = uuid;
		this.roomno = roomno;
		this.keycode = keycode;
	}

	public RoomControlCommandBean(String uuid, String roomno, String keycode, Serializable data) {
		super();
		this.uuid = uuid;
		this.roomno = roomno;
		this.keycode = keycode;
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomControlCommandBean [uuid=");
		builder.append(uuid);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", keycode=");
		builder.append(keycode);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

	public String getUuid() {
		return uuid;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getKeycode() {
		return keycode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
