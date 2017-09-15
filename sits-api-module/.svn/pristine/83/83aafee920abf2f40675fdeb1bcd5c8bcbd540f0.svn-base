package com.sidc.blackcore.api.sits.spare.request;

import java.io.Serializable;

public class SpareCategoryRequest implements Serializable {
	private static final long serialVersionUID = -3446320375199894379L;
	private int categoryid;
	private String status;
	private String langcode;

	public int getCategoryid() {
		return categoryid;
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
		builder.append("SpareCategoryRequest [categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public SpareCategoryRequest(int categoryid, String status, String langcode) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.langcode = langcode;
	}

}
