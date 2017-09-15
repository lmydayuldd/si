package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;

public class RoomServiceItemInfoBean implements Serializable {
	private static final long serialVersionUID = 2665977792801480588L;
	private int itemid;
	private int categoryid;
	private float price;

	public int getItemid() {
		return itemid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceItemInfoBean [itemid=");
		builder.append(itemid);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceItemInfoBean(int itemid, int categoryid, float price) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.price = price;
	}

}
