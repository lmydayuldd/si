package com.sidc.blackcore.api.sits.spare.bean;

import java.io.Serializable;

public class SpareOrderItemBean implements Serializable {
	private static final long serialVersionUID = -4793672946317810950L;
	private int itemid;
	private int qty;

	public int getItemid() {
		return itemid;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareOrderItemBean [itemid=");
		builder.append(itemid);
		builder.append(", qty=");
		builder.append(qty);
		builder.append("]");
		return builder.toString();
	}

	public SpareOrderItemBean(int itemid, int qty) {
		super();
		this.itemid = itemid;
		this.qty = qty;
	}

}
