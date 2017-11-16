package com.sidc.rcu.hmi.room.request;

import java.io.Serializable;

public class RcuRoomModuleUpdateRequest implements Serializable {
	private static final long serialVersionUID = -3833723318549313778L;
	private String roomno;
	private int groupid;

	public String getRoomno() {
		return roomno;
	}

	public int getGroupid() {
		return groupid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuRoomModuleUpdateRequest [roomno=");
		builder.append(roomno);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append("]");
		return builder.toString();
	}

	public RcuRoomModuleUpdateRequest(String roomno, int groupid) {
		super();
		this.roomno = roomno;
		this.groupid = groupid;
	}

}
