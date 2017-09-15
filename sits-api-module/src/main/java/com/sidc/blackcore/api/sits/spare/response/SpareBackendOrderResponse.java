package com.sidc.blackcore.api.sits.spare.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.spare.bean.SpareOrderLineBean;

public class SpareBackendOrderResponse implements Serializable {
	private static final long serialVersionUID = 2762177596132153823L;
	private int id;
	private String roomno;
	private String status;
	private float amount;
	private int qty;
	private String memo;
	private String guestfirstname;
	private String guestlasttname;
	private String createtime;
	private List<SpareOrderLineBean> itemlist = new ArrayList<SpareOrderLineBean>();

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

	public String getMemo() {
		return memo;
	}

	public String getGuestfirstname() {
		return guestfirstname;
	}

	public String getGuestlasttname() {
		return guestlasttname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public List<SpareOrderLineBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<SpareOrderLineBean> itemlist) {
		this.itemlist = itemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareBackendOrderResponse [id=");
		builder.append(id);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", status=");
		builder.append(status);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", qty=");
		builder.append(qty);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", guestfirstname=");
		builder.append(guestfirstname);
		builder.append(", guestlasttname=");
		builder.append(guestlasttname);
		builder.append(", createtime=");
		builder.append(createtime);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public SpareBackendOrderResponse(int id, String roomno, String status, float amount, int qty, String memo,
			String guestfirstname, String guestlasttname, String createtime) {
		super();
		this.id = id;
		this.roomno = roomno;
		this.status = status;
		this.amount = amount;
		this.qty = qty;
		this.memo = memo;
		this.guestfirstname = guestfirstname;
		this.guestlasttname = guestlasttname;
		this.createtime = createtime;
	}

}
