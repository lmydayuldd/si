package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceSetCategoryBean implements Serializable {
	private static final long serialVersionUID = -4962579587150426506L;
	private int categoryid;
	private int status;
	private int limitqty;
	private int sequence;
	private String name;
	private String description;
	private List<RoomServiceSetItemBean> itemlist = new ArrayList<RoomServiceSetItemBean>();

	public int getCategoryid() {
		return categoryid;
	}

	public int getStatus() {
		return status;
	}

	public int getLimitqty() {
		return limitqty;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<RoomServiceSetItemBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<RoomServiceSetItemBean> itemlist) {
		this.itemlist = itemlist;
	}

	public int getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceSetCategoryBean [categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", limitqty=");
		builder.append(limitqty);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceSetCategoryBean(int categoryid, int status, int limitqty, int sequence, String name,
			String description, List<RoomServiceSetItemBean> itemlist) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.limitqty = limitqty;
		this.sequence = sequence;
		this.name = name;
		this.description = description;
		this.itemlist = itemlist;
	}

	public RoomServiceSetCategoryBean(int categoryid, int status, int limitqty, int sequence, String name,
			String description) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.limitqty = limitqty;
		this.sequence = sequence;
		this.name = name;
		this.description = description;
	}

}
