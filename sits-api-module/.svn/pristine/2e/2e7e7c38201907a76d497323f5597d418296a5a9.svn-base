package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceSetBean implements Serializable {
	private static final long serialVersionUID = -8447410295326116316L;
	private int categoryid;
	private int limitqty;
	private List<RoomServiceItemIdBean> itemidlist = new ArrayList<RoomServiceItemIdBean>();

	public int getCategoryid() {
		return categoryid;
	}

	public int getLimitqty() {
		return limitqty;
	}

	public List<RoomServiceItemIdBean> getItemidlist() {
		return itemidlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceSetBean [categoryid=");
		builder.append(categoryid);
		builder.append(", limitqty=");
		builder.append(limitqty);
		builder.append(", itemidlist=");
		builder.append(itemidlist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceSetBean(int categoryid, int limitqty, List<RoomServiceItemIdBean> itemidlist) {
		super();
		this.categoryid = categoryid;
		this.limitqty = limitqty;
		this.itemidlist = itemidlist;
	}

}
