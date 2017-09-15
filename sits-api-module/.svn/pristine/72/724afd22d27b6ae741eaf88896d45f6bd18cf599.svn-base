package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopItemLangBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopPhotoUploadBean;

public class ShoppingItemInsertRequest implements Serializable {
	private static final long serialVersionUID = -2070917889857713191L;
	private String token;
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

	public String getToken() {
		return token;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public int getVendorid() {
		return vendorid;
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

	public List<ShopItemLangBean> getList() {
		return list;
	}

	public String getStatus() {
		return status;
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
		builder.append("ShoppingItemInsertRequest [token=");
		builder.append(token);
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
		builder.append("]");
		return builder.toString();
	}

	public ShoppingItemInsertRequest(String token, int categoryid, int vendorid, String status, int sequence,
			float price, float sellingprice, int qty, float weight, List<ShopItemLangBean> list,
			List<ShopPhotoUploadBean> photolist) {
		super();
		this.token = token;
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
	}

}
