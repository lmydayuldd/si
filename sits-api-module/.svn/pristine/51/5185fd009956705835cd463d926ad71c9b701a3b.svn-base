package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceItemCategoryBean implements Serializable {
	private static final long serialVersionUID = 4371807890243264589L;
	private int categoryid;
	private int limitqty;
	private int sequence;
	private List<RoomServiceSetItemInfoBean> itemlist = new ArrayList<RoomServiceSetItemInfoBean>();
	private List<RoomServiceLangBean> langs = new ArrayList<RoomServiceLangBean>();

	public int getCategoryid() {
		return categoryid;
	}

	public int getLimitqty() {
		return limitqty;
	}

	public int getSequence() {
		return sequence;
	}

	public List<RoomServiceSetItemInfoBean> getItemlist() {
		return itemlist;
	}

	public List<RoomServiceLangBean> getLangs() {
		return langs;
	}

	public void setItemlist(List<RoomServiceSetItemInfoBean> itemlist) {
		this.itemlist = itemlist;
	}

	public void setLangs(List<RoomServiceLangBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceItemCategoryBean [categoryid=");
		builder.append(categoryid);
		builder.append(", limitqty=");
		builder.append(limitqty);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceItemCategoryBean(int categoryid, int limitqty, int sequence) {
		super();
		this.categoryid = categoryid;
		this.limitqty = limitqty;
		this.sequence = sequence;
	}

}
