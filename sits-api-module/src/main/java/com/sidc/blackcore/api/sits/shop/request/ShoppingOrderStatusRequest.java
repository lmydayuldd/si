package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;

public class ShoppingOrderStatusRequest implements Serializable {
	private static final long serialVersionUID = 1641353453540412499L;
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
		builder.append("ShoppingOrderStatusRequest [token=");
		builder.append(token);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingOrderStatusRequest(String token, int orderid, String status) {
		super();
		this.token = token;
		this.orderid = orderid;
		this.status = status;
	}

}
