/**
 * 
 */
package com.derex.cm.stb.api.response;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class RoomRegisterResponse implements Serializable {

	private static final long serialVersionUID = 976330146340141611L;
	private String roomno;
	private String ip;

	public RoomRegisterResponse(String roomno, String ip) {
		super();
		this.roomno = roomno;
		this.ip = ip;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomRegisterResponse [roomno=\n");
		builder.append(roomno);
		builder.append(", ip=\n");
		builder.append(ip);
		builder.append("]");
		return builder.toString();
	}

}
