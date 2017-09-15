package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;

/**
 * 
 * @author Allen Huang
 *
 */
public class ShoppingRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3999802897567535441L;
	private int id;
	private int quantity;
	
	public ShoppingRequest(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingRequest [id=");
		builder.append(id);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
	
}
