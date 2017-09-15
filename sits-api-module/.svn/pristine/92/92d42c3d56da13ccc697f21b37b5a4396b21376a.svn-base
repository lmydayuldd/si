package com.sidc.blackcore.api.sits.shop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingItemResponse implements Serializable {
	private static final long serialVersionUID = -8930235006876709216L;
	private int itemid;
	private int categoryid;
	private int vendorid;
	private String status;
	private int sequence;
	private float price;
	private float sellingprice;
	private int qty;
	private float weight;
	private String creationtime;
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();
	private List<ShopLangBean> langs = new ArrayList<ShopLangBean>();

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

	public float getSellingprice() {
		return sellingprice;
	}

	public int getQty() {
		return qty;
	}

	public float getWeight() {
		return weight;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public List<ShopLangBean> getLangs() {
		return langs;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public void setLangs(List<ShopLangBean> langs) {
		this.langs = langs;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingItemResponse [itemid=");
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
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingItemResponse(int itemid, int categoryid, int vendorid, String status, int sequence, float price,
			float sellingprice, int qty, float weight, String creationtime) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.vendorid = vendorid;
		this.status = status;
		this.sequence = sequence;
		this.price = price;
		this.sellingprice = sellingprice;
		this.qty = qty;
		this.weight = weight;
		this.creationtime = creationtime;
	}

}
