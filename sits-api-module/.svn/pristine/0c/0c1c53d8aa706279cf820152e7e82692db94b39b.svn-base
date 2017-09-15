package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;

public class OrderStatusUpdateBean implements Serializable {
	private static final long serialVersionUID = 9210995705903501891L;
	private String functioncode;
	private String type;
	private String orderid;
	private String newstatus;

	public String getFunctioncode() {
		return functioncode;
	}

	public String getType() {
		return type;
	}

	public String getOrderid() {
		return orderid;
	}

	public String getNewstatus() {
		return newstatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderStatusUpdateBean [functioncode=");
		builder.append(functioncode);
		builder.append(", type=");
		builder.append(type);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", newstatus=");
		builder.append(newstatus);
		builder.append("]");
		return builder.toString();
	}

	public OrderStatusUpdateBean(String functioncode, String type, String orderid, String newstatus) {
		super();
		this.functioncode = functioncode;
		this.type = type;
		this.orderid = orderid;
		this.newstatus = newstatus;
	}

}
