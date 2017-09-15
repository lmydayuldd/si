package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingVendorInsertRequest implements Serializable {
	private static final long serialVersionUID = -1103511311043869292L;
	private String token;
	private int status;
	private String tex;
	private String email;
	private String address;
	private List<ShopLangBean> list = new ArrayList<ShopLangBean>();

	public String getToken() {
		return token;
	}

	public int getStatus() {
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

	public List<ShopLangBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingVendorInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", tex=");
		builder.append(tex);
		builder.append(", email=");
		builder.append(email);
		builder.append(", address=");
		builder.append(address);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingVendorInsertRequest(String token, int status, String tex, String email, String address,
			List<ShopLangBean> list) {
		super();
		this.token = token;
		this.status = status;
		this.tex = tex;
		this.email = email;
		this.address = address;
		this.list = list;
	}

}
