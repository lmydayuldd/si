/**
 * 
 */
package com.sidc.blackcore.api.ra.response;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class RoomInfoEnity implements Serializable {

	private static final long serialVersionUID = -2513532267965592753L;
	private String roomno;
	private String floor;
	private boolean isCheckin;
	private String rcugroup;
	
	public RoomInfoEnity(String roomno, String floor, boolean isCheckin, String rcugroup) {
		super();
		this.roomno = roomno;
		this.floor = floor;
		this.isCheckin = isCheckin;
		this.rcugroup = rcugroup;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomInfoEnity [roomno=");
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
