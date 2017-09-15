package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;

public class RoomServiceOrderStatusRequest implements Serializable {
	private static final long serialVersionUID = 4808638152610723301L;
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
		builder.append("RoomServiceOrderStatusRequest [token=");
		builder.append(token);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceOrderStatusRequest(String token, int orderid, String status) {
		super();
		this.token = token;
		this.orderid = orderid;
		this.status = status;
	}

}
