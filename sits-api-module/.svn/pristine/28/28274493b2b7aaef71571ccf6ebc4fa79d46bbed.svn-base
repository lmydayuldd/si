package com.sidc.blackcore.api.mobile.laundry.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryOrderLineBean;

public class LaundryBackendOrderListResponse implements Serializable {
	private static final long serialVersionUID = -1974033513769353258L;
	private int orderid;
	private String status;
	private String roomno;
	private String receivetime;
	private int classid;
	private String classname;
	private int totalpieces;
	private float subtotal;
	private float servicecharge;
	private float amount;
	private String memo;
	private String creationtime;
	private List<LaundryOrderLineBean> itemlist = new ArrayList<LaundryOrderLineBean>();

	public int getOrderid() {
		return orderid;
	}

	public String getStatus() {
		return status;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getReceivetime() {
		return receivetime;
	}

	public int getClassid() {
		return classid;
	}

	public String getClassname() {
		return classname;
	}

	public int getTotalpieces() {
		return totalpieces;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public float getServicecharge() {
		return servicecharge;
	}

	public float getAmount() {
		return amount;
	}

	public String getMemo() {
		return memo;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public List<LaundryOrderLineBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<LaundryOrderLineBean> itemlist) {
		this.itemlist = itemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryBackendOrderListResponse [orderid=");
		builder.append(orderid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", receivetime=");
		builder.append(receivetime);
		builder.append(", classid=");
		builder.append(classid);
		builder.append(", classname=");
		builder.append(classname);
		builder.append(", totalpieces=");
		builder.append(totalpieces);
		builder.append(", subtotal=");
		builder.append(subtotal);
		builder.append(", servicecharge=");
		builder.append(servicecharge);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public LaundryBackendOrderListResponse(int orderid, String status, String roomno, String receivetime, int classid,
			String classname, int totalpieces, float subtotal, float servicecharge, float amount, String memo,
			String creationtime) {
		super();
		this.orderid = orderid;
		this.status = status;
		this.roomno = roomno;
		this.receivetime = receivetime;
		this.classid = classid;
		this.classname = classname;
		this.totalpieces = totalpieces;
		this.subtotal = subtotal;
		this.servicecharge = servicecharge;
		this.amount = amount;
		this.memo = memo;
		this.creationtime = creationtime;
	}

}
