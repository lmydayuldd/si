package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;

public class LaundryOrderReceiveRequest implements Serializable {
	private static final long serialVersionUID = -2556576452844637869L;
	private String token;
	private int id;
	private String receivetime;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public String getReceivetime() {
		return receivetime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryOrderReceiveRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append(", receivetime=");
		builder.append(receivetime);
		builder.append("]");
		return builder.toString();
	}

	public LaundryOrderReceiveRequest(String token, int id, String receivetime) {
		super();
		this.token = token;
		this.id = id;
		this.receivetime = receivetime;
	}

}
