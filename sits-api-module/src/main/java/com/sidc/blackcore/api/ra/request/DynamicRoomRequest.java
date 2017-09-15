/**
 * 
 */
package com.sidc.blackcore.api.ra.request;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class DynamicRoomRequest implements Serializable {

	private static final long serialVersionUID = 5788827806625750941L;
	private String roomno;
	private String ip;

	public String getRoomno() {
		return roomno;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DynamicRoomRequest [roomno=");
		builder.append(roomno);
		builder.append(", ip=");
		builder.append(ip);
		builder.append("]");
		return builder.toString();
	}

	public DynamicRoomRequest(String roomno, String ip) {
		super();
		this.roomno = roomno;
		this.ip = ip;
	}

}
