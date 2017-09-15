package com.sidc.blackcore.api.sits.spare.bean;

import java.io.Serializable;

public class SpareOrderLineBean implements Serializable {
	private static final long serialVersionUID = 382889394689825640L;
	private int categoryid;
	private String categoryname;
	private int itemid;
	private String itemname;
	private float amount;
	private int qty;

	public int getCategoryid() {
		return categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public int getItemid() {
		return itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public float getAmount() {
		return amount;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareOrderLineBean [categoryid=");
		builder.append(categoryid);
		builder.append(", categoryname=");
		builder.append(categoryname);
		builder.append(", itemid=");
		builder.append(itemid);
		builder.append(", itemname=");
		builder.append(itemname);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", qty=");
		builder.append(qty);
		builder.append("]");
		return builder.toString();
	}

	public SpareOrderLineBean(int categoryid, String categoryname, int itemid, String itemname, float amount, int qty) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.itemid = itemid;
		this.itemname = itemname;
		this.amount = amount;
		this.qty = qty;
	}

}
