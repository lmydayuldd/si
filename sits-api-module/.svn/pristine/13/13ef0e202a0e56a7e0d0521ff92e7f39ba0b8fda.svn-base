package com.sidc.blackcore.api.sits.shop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingVendorResponse implements Serializable {
	private static final long serialVersionUID = -9153960014096724118L;
	private int vendorid;
	private String status;
	private String tex;
	private String email;
	private String address;
	private String creationtime;
	private List<ShopLangBean> langs = new ArrayList<ShopLangBean>();

	public int getVendorid() {
		return vendorid;
	}

	public String getStatus() {
		return status;
	}

	public String getTex() {
		return tex;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public List<ShopLangBean> getLangs() {
		return langs;
	}

	public void setLangs(List<ShopLangBean> langs) {
		this.langs = langs;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingVendorResponse [vendorid=");
		builder.append(vendorid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", tex=");
		builder.append(tex);
		builder.append(", email=");
		builder.append(email);
		builder.append(", address=");
		builder.append(address);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingVendorResponse(int vendorid, String status, String tex, String email, String address,
			String creationtime) {
		super();
		this.vendorid = vendorid;
		this.status = status;
		this.tex = tex;
		this.email = email;
		this.address = address;
		this.creationtime = creationtime;
	}

	public ShoppingVendorResponse(int vendorid, String status, String tex, String email, String address,
			String creationtime, List<ShopLangBean> langs) {
		super();
		this.vendorid = vendorid;
		this.status = status;
		this.tex = tex;
		this.email = email;
		this.address = address;
		this.creationtime = creationtime;
		this.langs = langs;
	}

}
