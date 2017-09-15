package com.sidc.blackcore.api.sits.shop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopTypeInfoBean;

public class ShopListResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5208682426112920179L;
	private String currency;
	private List<ShopTypeInfoBean> list = new ArrayList<ShopTypeInfoBean>();

	public List<ShopTypeInfoBean> getList() {
		return list;
	}

	public String getCurrency() {
		return currency;
	}

	public ShopListResponse(String currency, List<ShopTypeInfoBean> list) {
		super();
		this.currency = currency;
		this.list = list;
	}

}
