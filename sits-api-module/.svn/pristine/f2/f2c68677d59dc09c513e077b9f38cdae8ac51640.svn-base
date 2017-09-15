package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;

public class ActivityPaymentStatusUpdateRequest implements Serializable {
	private static final long serialVersionUID = -2001256917582713033L;
	private String token;
	private int orderid;
	private String paymentstatus;

	public String getToken() {
		return token;
	}

	public int getOrderid() {
		return orderid;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityPaymentStatusUpdateRequest [token=");
		builder.append(token);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", paymentstatus=");
		builder.append(paymentstatus);
		builder.append("]");
		return builder.toString();
	}

	public ActivityPaymentStatusUpdateRequest(String token, int orderid, String paymentstatus) {
		super();
		this.token = token;
		this.orderid = orderid;
		this.paymentstatus = paymentstatus;
	}

}
