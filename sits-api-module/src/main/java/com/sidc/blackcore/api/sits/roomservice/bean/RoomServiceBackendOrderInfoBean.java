package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceBackendOrderInfoBean implements Serializable {
	private static final long serialVersionUID = 8675461479286274312L;
	private int id;
	private String roomno;
	private String status;
	private float amount;
	private int qty;
	private String guestfirstname;
	private String guestlastname;
	private String createtime;
	private String expectedtime;
	private String memo;
	private List<RoomServiceOrderLineInfoBean> itemlist = new ArrayList<RoomServiceOrderLineInfoBean>();

	public int getId() {
		return id;
	}

	public String getRoomno() {
		return roomno;
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

	public List<RoomServiceOrderLineInfoBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<RoomServiceOrderLineInfoBean> itemlist) {
		this.itemlist = itemlist;
	}

	public String getMemo() {
		return memo;
	}

	public String getExpectedtime() {
		return expectedtime;
	}

	public String getGuestfirstname() {
		return guestfirstname;
	}

	public String getGuestlastname() {
		return guestlastname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceBackendOrderInfoBean [id=");
		builder.append(id);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", status=");
		builder.append(status);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", qty=");
		builder.append(qty);
		builder.append(", guestfirstname=");
		builder.append(guestfirstname);
		builder.append(", guestlastname=");
		builder.append(guestlastname);
		builder.append(", createtime=");
		builder.append(createtime);
		builder.append(", expectedtime=");
		builder.append(expectedtime);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceBackendOrderInfoBean(int id, String roomno, String status, float amount, int qty,
			String guestfirstname, String guestlastname, String createtime, String expectedtime, String memo) {
		super();
		this.id = id;
		this.roomno = roomno;
		this.status = status;
		this.amount = amount;
		this.qty = qty;
		this.guestfirstname = guestfirstname;
		this.guestlastname = guestlastname;
		this.createtime = createtime;
		this.expectedtime = expectedtime;
		this.memo = memo;
	}

}
