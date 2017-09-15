package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;

public class RoomServiceItemIdBean implements Serializable {
	private static final long serialVersionUID = 8089456119924552189L;
	private int itemid;

	public int getItemid() {
		return itemid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceItemIdBean [itemid=");
		builder.append(itemid);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceItemIdBean(int itemid) {
		super();
		this.itemid = itemid;
	}

}
