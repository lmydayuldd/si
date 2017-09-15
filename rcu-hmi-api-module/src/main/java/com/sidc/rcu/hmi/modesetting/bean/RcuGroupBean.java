package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;

public class RcuGroupBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4163191217572087837L;
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupBean [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
