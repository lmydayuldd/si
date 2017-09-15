package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;

public class ShopListRequest implements Serializable {
	private static final long serialVersionUID = 6645915873624165092L;
	private String hotelid;
	private String langcode;

	public String getHotelid() {
		return hotelid;
	}

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopListRequest [hotelid=");
		builder.append(hotelid);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public ShopListRequest(String hotelid, String langcode) {
		super();
		this.hotelid = hotelid;
		this.langcode = langcode;
	}
}
