package com.sidc.blackcore.api.mobile.laundry.bean;

import java.io.Serializable;

public class LaundryOrderLineBean implements Serializable {
	private static final long serialVersionUID = 4539825849971737596L;
	private int typeid;
	private String typename;
	private int itemid;
	private String itemname;
	private int washtypeid;
	private String washtypename;
	private int returntypeid;
	private String returntypename;
	private int piece;
	private float price;

	public int getTypeid() {
		return typeid;
	}

	public String getTypename() {
		return typename;
	}

	public int getItemid() {
		return itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public int getWashtypeid() {
		return washtypeid;
	}

	public String getWashtypename() {
		return washtypename;
	}

	public int getReturntypeid() {
		return returntypeid;
	}

	public String getReturntypename() {
		return returntypename;
	}

	public int getPiece() {
		return piece;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryOrderLineBean [typeid=");
		builder.append(typeid);
		builder.append(", typename=");
		builder.append(typename);
		builder.append(", itemid=");
		builder.append(itemid);
		builder.append(", itemname=");
		builder.append(itemname);
		builder.append(", washtypeid=");
		builder.append(washtypeid);
		builder.append(", washtypename=");
		builder.append(washtypename);
		builder.append(", returntypeid=");
		builder.append(returntypeid);
		builder.append(", returntypename=");
		builder.append(returntypename);
		builder.append(", piece=");
		builder.append(piece);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	public LaundryOrderLineBean(int typeid, String typename, int itemid, String itemname, int washtypeid,
			String washtypename, int returntypeid, String returntypename, int piece, float price) {
		super();
		this.typeid = typeid;
		this.typename = typename;
		this.itemid = itemid;
		this.itemname = itemname;
		this.washtypeid = washtypeid;
		this.washtypename = washtypename;
		this.returntypeid = returntypeid;
		this.returntypename = returntypename;
		this.piece = piece;
		this.price = price;
	}

	public LaundryOrderLineBean(int typeid, int itemid, int piece, float price) {
		super();
		this.typeid = typeid;
		this.itemid = itemid;
		this.piece = piece;
		this.price = price;
	}

}
