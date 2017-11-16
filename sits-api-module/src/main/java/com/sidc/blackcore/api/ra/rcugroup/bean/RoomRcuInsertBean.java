/**
 * 
 */
package com.sidc.blackcore.api.ra.rcugroup.bean;

import java.util.List;

public class RoomRcuInsertBean implements java.io.Serializable {
	private static final long serialVersionUID = 1105528264446454892L;
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
		builder.append("RoomRcuInsertBean [groupid=");
		builder.append(groupid);
		builder.append(", rooms=");
		builder.append(rooms);
		builder.append("]");
		return builder.toString();
	}

	public RoomRcuInsertBean(int groupid, List<String> rooms) {
		super();
		this.groupid = groupid;
		this.rooms = rooms;
	}

}
