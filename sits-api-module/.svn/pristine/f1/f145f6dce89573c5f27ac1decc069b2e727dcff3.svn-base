package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;

public class RoomServiceCategoryRequest implements Serializable {
	private static final long serialVersionUID = -7037694756076704157L;
	private int categoryid;
	private int referid;
	private String status;
	private String langcode;

	public int getCategoryid() {
		return categoryid;
	}

	public int getReferid() {
		return referid;
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
		builder.append("RoomServiceCategoryRequest [categoryid=");
		builder.append(categoryid);
		builder.append(", referid=");
		builder.append(referid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceCategoryRequest(int categoryid, int referid, String status, String langcode) {
		super();
		this.categoryid = categoryid;
		this.referid = referid;
		this.status = status;
		this.langcode = langcode;
	}

}
