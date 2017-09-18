package com.sidc.rcu.hmi.bean;

import java.io.Serializable;

public class RcuInfoBean implements Serializable {

	private static final long serialVersionUID = 5550236420040494594L;
	private String uuid;
	private String roomNo;

	public String getUuid() {
		return uuid;
	}

	public String getRoomNo() {
		return roomNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuInfoBean [uuid=");
		builder.append(uuid);
		builder.append(", roomNo=");
		builder.append(roomNo);
		builder.append("]");
		return builder.toString();
	}

	public RcuInfoBean(String uuid, String roomNo) {
		super();
		this.uuid = uuid;
		this.roomNo = roomNo;
	}
}
