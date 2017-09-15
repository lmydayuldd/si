package com.sidc.blackcore.api.sits.shop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopOrderLineBean;

public class ShoppingOrderResponse implements Serializable {
	private static final long serialVersionUID = -7970241922083339346L;
	private int id;
	private String status;
	private float amount;
	private int qty;
	private String createtime;
	private List<ShopOrderLineBean> itemlist = new ArrayList<ShopOrderLineBean>();

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public float getAmount() {
		return amount;
	}

	public int getQty() {
		return qty;
	}

	public String getCreatetime() {
		return createtime;
	}

	public List<ShopOrderLineBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<ShopOrderLineBean> itemlist) {
		this.itemlist = itemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingOrderResponse [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", qty=");
		builder.append(qty);
		builder.append(", createtime=");
		builder.append(createtime);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingOrderResponse(int id, String status, float amount, int qty, String createtime) {
		super();
		this.id = id;
		this.status = status;
		this.amount = amount;
		this.qty = qty;
		this.createtime = createtime;
	}

}
