package com.sidc.blackcore.api.sits.shop.bean;

import java.io.Serializable;

public class TypeToShopBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6340955651765874578L;
	private int shop_id;
	private int type_id;
	private float price;
	private int seq;
	private String post_location;
	private String dock_post_location;

	public int getShop_id() {
		return shop_id;
	}

	public int getType_id() {
		return type_id;
	}

	public float getPrice() {
		return price;
	}

	public int getSeq() {
		return seq;
	}

	public String getPost_location() {
		return post_location;
	}

	public String getDock_post_location() {
		return dock_post_location;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeToShopBean [shop_id=");
		builder.append(shop_id);
		builder.append(", type_id=");
		builder.append(type_id);
		builder.append(", price=");
		builder.append(price);
		builder.append(", seq=");
		builder.append(seq);
		builder.append(", post_location=");
		builder.append(post_location);
		builder.append(", dock_post_location=");
		builder.append(dock_post_location);
		builder.append("]");
		return builder.toString();
	}

	public TypeToShopBean(int shop_id, int type_id, float price, int seq, String post_location,
			String dock_post_location) {
		super();
		this.shop_id = shop_id;
		this.type_id = type_id;
		this.price = price;
		this.seq = seq;
		this.post_location = post_location;
		this.dock_post_location = dock_post_location;
	}

}
