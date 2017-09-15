package com.sidc.blackcore.api.sits.spare.request;

import java.io.Serializable;

public class SpareItemRequest implements Serializable {
	private static final long serialVersionUID = -4196115026925441072L;
	private int categoryid;
	private int itemid;
	private String status;
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

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareItemRequest [categoryid=");
		builder.append(categoryid);
		builder.append(", itemid=");
		builder.append(itemid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public SpareItemRequest(int categoryid, int itemid, String status, String langcode) {
		super();
		this.categoryid = categoryid;
		this.itemid = itemid;
		this.status = status;
		this.langcode = langcode;
	}

}
