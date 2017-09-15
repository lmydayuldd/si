package com.sidc.blackcore.api.mobile.laundry.bean;

import java.io.Serializable;

public class LaundryReturnTypeBean implements Serializable {
	private static final long serialVersionUID = 8258049302091416691L;
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
		builder.append("LaundryReturnTypeBean [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public LaundryReturnTypeBean(int id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

}
