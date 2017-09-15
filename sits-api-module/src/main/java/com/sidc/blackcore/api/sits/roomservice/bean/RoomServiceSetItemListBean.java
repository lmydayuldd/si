package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceSetItemListBean implements Serializable {
	private static final long serialVersionUID = -8812025517386756154L;
	private List<RoomServiceOrderSetItemBean> itemlist = new ArrayList<RoomServiceOrderSetItemBean>();

	public RoomServiceSetItemListBean(List<RoomServiceOrderSetItemBean> itemlist) {
		super();
		this.itemlist = itemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceSetItemListBean [itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public List<RoomServiceOrderSetItemBean> getItemlist() {
		return itemlist;
	}
}
