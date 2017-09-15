package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;

public class ShoppingItemRequest implements Serializable {
	private static final long serialVersionUID = -7932194259482479544L;
	private int itemid;
	private int categoryid;
	private int vendorid;
	private String status;
	private String langcode;

	public int getItemid() {
		return itemid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public int getVendorid() {
		return vendorid;
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
		builder.append("ShoppingItemRequest [itemid=");
		builder.append(itemid);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", vendorid=");
		builder.append(vendorid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingItemRequest(int itemid, int categoryid, int vendorid, String status, String langcode) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.vendorid = vendorid;
		this.status = status;
		this.langcode = langcode;
	}

}
