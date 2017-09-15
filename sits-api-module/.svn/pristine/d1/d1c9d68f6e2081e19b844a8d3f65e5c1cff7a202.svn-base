package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceOrderLineInfoBean implements Serializable {
	private static final long serialVersionUID = 7301576160977730627L;
	private int id;
	private int categoryid;
	private String categoryname;
	private int itemid;
	private String itemname;
	private float amount;
	private int qty;
	private List<RoomServiceOrderLineInfoBean> setitemlist = new ArrayList<RoomServiceOrderLineInfoBean>();

	public int getId() {
		return id;
	}

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

	public List<RoomServiceOrderLineInfoBean> getSetitemlist() {
		return setitemlist;
	}

	public void setSetitemlist(List<RoomServiceOrderLineInfoBean> setitemlist) {
		this.setitemlist = setitemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceOrderLineInfoBean [id=");
		builder.append(id);
		builder.append(", categoryid=");
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
		builder.append(", setitemlist=");
		builder.append(setitemlist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceOrderLineInfoBean(int id, int categoryid, String categoryname, int itemid, String itemname,
			float amount, int qty) {
		super();
		this.id = id;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.itemid = itemid;
		this.itemname = itemname;
		this.amount = amount;
		this.qty = qty;
	}

}
