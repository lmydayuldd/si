package com.derex.cm.stb.api.request;

import java.io.Serializable;

public class StbRcuCommandRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -483768338180933898L;
	private String roomno;
	private String command;

	public StbRcuCommandRequest(String roomno, String command) {
		super();
		this.roomno = roomno;
		this.command = command;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getCommand() {
		return command;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbRcuCommandRequest [roomno=");
		builder.append(roomno);
		builder.append(", command=");
		builder.append(command);
		builder.append("]");
		return builder.toString();
	}

}
