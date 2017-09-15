package com.sidc.blackcore.api.mobile.activity.bean;

import java.io.Serializable;

public class ActivityToFeeBean implements Serializable {

	private static final long serialVersionUID = -9081887149643494108L;
	private int feeid;
	private String price;

	public int getFeeid() {
		return feeid;
	}

	public String getPrice() {
		return price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityToFeeBean [feeid=");
		builder.append(feeid);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	public ActivityToFeeBean(int feeid, String price) {
		super();
		this.feeid = feeid;
		this.price = price;
	}

}
