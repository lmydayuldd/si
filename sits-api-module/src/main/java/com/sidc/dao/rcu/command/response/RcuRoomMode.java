package com.sidc.dao.rcu.command.response;

import java.io.Serializable;

public class RcuRoomMode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4655575566731282937L;
	private String roomno;
	private String mode;
	public RcuRoomMode(String roomno, String mode) {
		// TODO Auto-generated constructor stub
		this.roomno = roomno;
		this.mode = mode;
	}
	
	public String getRoomno() {
		return roomno;
	}
	
	public String getMode() {
		return mode;
	}

	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("RcuRoomMode [roomno=\n");
		builder.append(roomno);
		builder.append(", mode=\n");
		builder.append(mode);
		builder.append("]");
		return builder.toString();
	}
}
