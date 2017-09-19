package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.List;

import com.sidc.rcu.hmi.request.RcuRoomStatusRequest;

public class RcuRoomBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4170765070212399205L;
	private List<RcuRoomStatusRequest> rooms;

	public List<RcuRoomStatusRequest> getRooms() {
		return rooms;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuRoomBean [rooms=");
		builder.append(rooms);
		builder.append("]");
		return builder.toString();
	}

	public RcuRoomBean(List<RcuRoomStatusRequest> rooms) {
		super();
		this.rooms = rooms;
	}

}
