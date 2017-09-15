package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopItemLangBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopPhotoUploadBean;

public class ShoppingItemUpdateRequest implements Serializable {
	private static final long serialVersionUID = -2065364575096249191L;
	private String token;
	private int itemid;
	private int categoryid;
	private int vendorid;
	private String status;
	private int sequence;
	private float price;
	private float sellingprice;
	private int qty;
	private float weight;
	private List<ShopItemLangBean> list = new ArrayList<ShopItemLangBean>();
	private List<ShopPhotoUploadBean> photolist = new ArrayList<ShopPhotoUploadBean>();
	private boolean photoupdate;

	public String getToken() {
		return token;
	}

	public int getItemid() {
		return itemid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public int getVendorid() {
		return vendorid;
	}

	public String getStatus() {
		return status;
	}

	public int getSequence() {
		return sequence;
	}

	public float getPrice() {
		return price;
	}

	public float getSellingprice() {
		return sellingprice;
	}

	public int getQty() {
		return qty;
	}

	public float getWeight() {
		return weight;
	}

	public boolean isPhotoupdate() {
		return photoupdate;
	}

	public List<ShopItemLangBean> getList() {
		return list;
	}

	public List<ShopPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<ShopPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingItemUpdateRequest [token=");
		builder.append(token);
		builder.append(", itemid=");
		builder.append(itemid);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", vendorid=");
		builder.append(vendorid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", price=");
		builder.append(price);
		builder.append(", sellingprice=");
		builder.append(sellingprice);
		builder.append(", qty=");
		builder.append(qty);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", list=");
		builder.append(list);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", photoupdate=");
		builder.append(photoupdate);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingItemUpdateRequest(String token, int itemid, int categoryid, int vendorid, String status,
			int sequence, float price, float sellingprice, int qty, float weight, List<ShopItemLangBean> list,
			List<ShopPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.token = token;
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.vendorid = vendorid;
		this.status = status;
		this.sequence = sequence;
		this.price = price;
		this.sellingprice = sellingprice;
		this.qty = qty;
		this.weight = weight;
		this.list = list;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

}
