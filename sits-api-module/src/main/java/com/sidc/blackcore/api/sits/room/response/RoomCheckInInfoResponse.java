/**
 * 
 */
package com.sidc.blackcore.api.sits.room.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomCheckInInfoResponse implements Serializable {
	private static final long serialVersionUID = -4621951642207966239L;
	private List<String> roomlist = new ArrayList<String>();
	private List<String> grouplist = new ArrayList<String>();

	public List<String> getRoomlist() {
		return roomlist;
	}

	public List<String> getGrouplist() {
		return grouplist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomCheckInInfoResponse [roomlist=");
		builder.append(roomlist);
		builder.append(", grouplist=");
		builder.append(grouplist);
		builder.append("]");
		return builder.toString();
	}

	public RoomCheckInInfoResponse(List<String> roomlist, List<String> grouplist) {
		super();
		this.roomlist = roomlist;
		this.grouplist = grouplist;
	}

}
