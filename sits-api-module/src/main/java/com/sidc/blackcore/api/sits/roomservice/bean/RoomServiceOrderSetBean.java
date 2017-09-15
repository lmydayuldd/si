package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceOrderSetBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8679904561789122668L;
	private int setitemid;
	private int qty;
	private List<RoomServiceSetItemListBean> iteminfo = new ArrayList<RoomServiceSetItemListBean>();

	public int getSetitemid() {
		return setitemid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceOrderSetBean [setitemid=");
		builder.append(setitemid);
		builder.append(", qty=");
		builder.append(qty);
		builder.append(", iteminfo=");
		builder.append(iteminfo);
		builder.append("]");
		return builder.toString();
	}

	public int getQty() {
		return qty;
	}

	public List<RoomServiceSetItemListBean> getIteminfo() {
		return iteminfo;
	}

	public RoomServiceOrderSetBean(int setitemid, int qty, List<RoomServiceSetItemListBean> iteminfo,
			List<RoomServiceOrderSetItemBean> itemlist) {
		super();
		this.setitemid = setitemid;
		this.qty = qty;
		this.iteminfo = iteminfo;
	}

}
