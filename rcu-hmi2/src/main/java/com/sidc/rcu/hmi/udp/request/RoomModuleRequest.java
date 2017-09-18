package com.sidc.rcu.hmi.udp.request;

import java.io.Serializable;

public class RoomModuleRequest implements Serializable {
	private static final long serialVersionUID = 1164196699817565830L;

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public RoomModuleRequest(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomModuleRequest [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
