package com.sidc.rcu.hmi.room.request;

import java.io.Serializable;
import java.util.List;

public class RcuRoomModuleInsertRequest implements Serializable {
	private static final long serialVersionUID = -968808882819239761L;
	private int groupid;
	private List<String> rooms;

	public int getGroupid() {
		return groupid;
	}

	public List<String> getRooms() {
		return rooms;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuRoomModuleInsertRequest [groupid=");
		builder.append(groupid);
		builder.append(", rooms=");
		builder.append(rooms);
		builder.append("]");
		return builder.toString();
	}

	public RcuRoomModuleInsertRequest(int groupid, List<String> rooms) {
		super();
		this.groupid = groupid;
		this.rooms = rooms;
	}
}
