package com.sidc.rcu.hmi.room.request;

import java.io.Serializable;

public class RcuRoomInfoRequest implements Serializable {
	private static final long serialVersionUID = -6705295774935436309L;
	private String roomno;
	private String floor;
	private boolean isCheckin;
	private String rcugroup;

	public String getRoomno() {
		return roomno;
	}

	public boolean isCheckin() {
		return isCheckin;
	}

	public String getRcugroup() {
		return rcugroup;
	}

	public String getFloor() {
		return floor;
	}

	public RcuRoomInfoRequest(String roomno, String floor, boolean isCheckin, String rcugroup) {
		super();
		this.roomno = roomno;
		this.floor = floor;
		this.isCheckin = isCheckin;
		this.rcugroup = rcugroup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuRoomInfoRequest [roomno=");
		builder.append(roomno);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", isCheckin=");
		builder.append(isCheckin);
		builder.append(", rcugroup=");
		builder.append(rcugroup);
		builder.append("]");
		return builder.toString();
	}
}
