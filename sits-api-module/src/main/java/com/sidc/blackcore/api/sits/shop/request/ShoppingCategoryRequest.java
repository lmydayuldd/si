package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;

public class ShoppingCategoryRequest implements Serializable {
	private static final long serialVersionUID = 851171273487505393L;
	private int categoryid;
	private String status;
	private int referid;
	private String langcode;
	

	public int getCategoryid() {
		return categoryid;
	}

	public String getStatus() {
		return status;
	}

	public int getReferid() {
		return referid;
	}

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingCategoryRequest [categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", referid=");
		builder.append(referid);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingCategoryRequest(int categoryid, String status, int referid, String langcode) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.referid = referid;
		this.langcode = langcode;
	}

}
