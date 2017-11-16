package com.sidc.rcu.hmi.room.response;

import java.io.Serializable;

public class RoomInfoResponse implements Serializable {
	private static final long serialVersionUID = -3391199602057492624L;
	private String roomno;
	private String floor;
	private String rcugroup;

	public String getRoomno() {
		return roomno;
	}

	public String getFloor() {
		return floor;
	}

	public String getRcugroup() {
		return rcugroup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomInfoResponse [roomno=");
		builder.append(roomno);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", rcugroup=");
		builder.append(rcugroup);
		builder.append("]");
		return builder.toString();
	}

	public RoomInfoResponse(String roomno, String floor, String rcugroup) {
		super();
		this.roomno = roomno;
		this.floor = floor;
		this.rcugroup = rcugroup;
	}

}
