package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;

public class RoomServiceOrderLineBean implements Serializable {
	private static final long serialVersionUID = -6343703469642788997L;
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
		builder.append("RoomServiceOrderLineBean [itemid=");
		builder.append(itemid);
		builder.append(", qty=");
		builder.append(qty);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceOrderLineBean(int itemid, int qty) {
		super();
		this.itemid = itemid;
		this.qty = qty;
	}

}
