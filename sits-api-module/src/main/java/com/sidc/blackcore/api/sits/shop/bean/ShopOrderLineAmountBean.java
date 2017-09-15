package com.sidc.blackcore.api.sits.shop.bean;

import java.io.Serializable;

public class ShopOrderLineAmountBean implements Serializable {
	private static final long serialVersionUID = 2003851930539050033L;
	private int itemid;
	private float amount;
	private int qty;

	public int getItemid() {
		return itemid;
	}

	public float getAmount() {
		return amount;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopOrderLineAmountBean [itemid=");
		builder.append(itemid);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", qty=");
		builder.append(qty);
		builder.append("]");
		return builder.toString();
	}

	public ShopOrderLineAmountBean(int itemid, float amount, int qty) {
		super();
		this.itemid = itemid;
		this.amount = amount;
		this.qty = qty;
	}

}
