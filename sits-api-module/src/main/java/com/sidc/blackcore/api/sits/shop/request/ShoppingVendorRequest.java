package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;

public class ShoppingVendorRequest implements Serializable {
	private static final long serialVersionUID = -845037926986140375L;
	private int vendorid;
	private String status;
	private String vendorname;
	private String langcode;

	public int getVendorid() {
		return vendorid;
	}

	public String getStatus() {
		return status;
	}

	public String getVendorname() {
		return vendorname;
	}

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingVendorRequest [vendorid=");
		builder.append(vendorid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", vendorname=");
		builder.append(vendorname);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingVendorRequest(int vendorid, String status, String vendorname, String langcode) {
		super();
		this.vendorid = vendorid;
		this.status = status;
		this.vendorname = vendorname;
		this.langcode = langcode;
	}

}
