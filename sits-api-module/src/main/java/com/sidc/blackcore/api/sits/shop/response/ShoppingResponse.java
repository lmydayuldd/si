package com.sidc.blackcore.api.sits.shop.response;

import java.io.Serializable;

/**
 * 
 * @author Allen Huang
 *
 */
public class ShoppingResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7361832244322460373L;
	private String orderid;
	private float totalamount;
	
	public ShoppingResponse(String orderid, float totalamount) {
		super();
		this.orderid = orderid;
		this.totalamount = totalamount;
	}

	public String getOrderid() {
		return orderid;
	}

	public float getTotalamount() {
		return totalamount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingResponse [orderid=");
		builder.append(orderid);
		builder.append(", totalamount=");
		builder.append(totalamount);
		builder.append("]");
		return builder.toString();
	}
	
}
