package com.sidc.blackcore.api.sits.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShopTypeInfoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4674425250594434477L;
	private int catalogueid;
	private int sequence;
	private String catalogue;
	private List<ShopItemInfoBean> shoppinglist = new ArrayList<ShopItemInfoBean>();

	public int getCatalogueid() {
		return catalogueid;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public List<ShopItemInfoBean> getShoppinglist() {
		return shoppinglist;
	}

	public void setShoppinglist(List<ShopItemInfoBean> shoppinglist) {
		this.shoppinglist = shoppinglist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopTypeInfoBean [catalogueid=");
		builder.append(catalogueid);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", catalogue=");
		builder.append(catalogue);
		builder.append(", shoppinglist=");
		builder.append(shoppinglist);
		builder.append("]");
		return builder.toString();
	}

	public ShopTypeInfoBean(int catalogueid, int sequence, String catalogue) {
		super();
		this.catalogueid = catalogueid;
		this.sequence = sequence;
		this.catalogue = catalogue;
	}

}
