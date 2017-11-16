package com.sidc.blackcore.api.ra.rcugroup.request;

import java.util.List;

public class RoomRcuGroupUpdateRequest implements java.io.Serializable {
	private static final long serialVersionUID = -5101919796048171398L;
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
		builder.append("RoomRcuGroupUpdateRequest [groupid=");
		builder.append(groupid);
		builder.append("]");
		return builder.toString();
	}

	public RoomRcuGroupUpdateRequest(int groupid, List<String> rooms) {
		super();
		this.groupid = groupid;
		this.rooms = rooms;
	}

}
