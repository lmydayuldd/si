package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;

public class RoomServiceItemRequest implements Serializable {
	private static final long serialVersionUID = -7513150043438082207L;
	private int categoryid;
	private int itemid;
	private String status;
	private String type;
	private String langcode;

	public int getCategoryid() {
		return categoryid;
	}

	public int getItemid() {
		return itemid;
	}

	public String getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceItemRequest [categoryid=");
		builder.append(categoryid);
		builder.append(", itemid=");
		builder.append(itemid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceItemRequest(int categoryid, int itemid, String status, String type, String langcode) {
		super();
		this.categoryid = categoryid;
		this.itemid = itemid;
		this.status = status;
		this.type = type;
		this.langcode = langcode;
	}

}
