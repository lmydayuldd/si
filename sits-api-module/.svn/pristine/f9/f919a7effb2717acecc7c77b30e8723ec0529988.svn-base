package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;

public class ActivityStatusUpdateRequest implements Serializable {
	private static final long serialVersionUID = -2001256917582713033L;
	private String token;
	private int orderid;
	private String status;

	public String getToken() {
		return token;
	}

	public int getOrderid() {
		return orderid;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityStatusUpdateRequest [token=");
		builder.append(token);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public ActivityStatusUpdateRequest(String token, int orderid, String status) {
		super();
		this.token = token;
		this.orderid = orderid;
		this.status = status;
	}

}
