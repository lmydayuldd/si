package com.sidc.blackcore.api.mobile.laundry.bean;

import java.io.Serializable;

public class LaundryWashTypeStatusBean implements Serializable {
	private static final long serialVersionUID = -5274512778390930191L;
	private int id;
	private int status;

	public int getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryWashTypeStatusBean [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public LaundryWashTypeStatusBean(int id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

}
