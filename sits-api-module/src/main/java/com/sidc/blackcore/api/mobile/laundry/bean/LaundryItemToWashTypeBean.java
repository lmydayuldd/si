package com.sidc.blackcore.api.mobile.laundry.bean;

import java.io.Serializable;

public class LaundryItemToWashTypeBean implements Serializable {
	private static final long serialVersionUID = -8367018221461334426L;
	private int washtypeid;
	private int price;

	public int getWashtypeid() {
		return washtypeid;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryItemToWashTypeBean [washtypeid=");
		builder.append(washtypeid);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	public LaundryItemToWashTypeBean(int washtypeid, int price) {
		super();
		this.washtypeid = washtypeid;
		this.price = price;
	}

}
