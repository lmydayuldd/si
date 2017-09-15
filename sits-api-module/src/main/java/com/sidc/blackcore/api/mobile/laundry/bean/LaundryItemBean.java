package com.sidc.blackcore.api.mobile.laundry.bean;

import java.io.Serializable;

public class LaundryItemBean implements Serializable {
	private static final long serialVersionUID = 3802973735828710292L;
	private int id;
	private int status;
	private int typeid;

	public int getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public int getTypeid() {
		return typeid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryItemBean [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", typeid=");
		builder.append(typeid);
		builder.append("]");
		return builder.toString();
	}

	public LaundryItemBean(int id, int status, int typeid) {
		super();
		this.id = id;
		this.status = status;
		this.typeid = typeid;
	}

}
