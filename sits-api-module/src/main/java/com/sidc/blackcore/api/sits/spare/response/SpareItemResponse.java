package com.sidc.blackcore.api.sits.spare.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;

public class SpareItemResponse implements Serializable {
	private static final long serialVersionUID = 727389926687728052L;
	private int itemid;
	private int categoryid;
	private String status;
	private int sequence;
	private float price;
	private int qty;
	private String creationtime;
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();
	private List<SpareLangBean> langs = new ArrayList<SpareLangBean>();

	public int getItemid() {
		return itemid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public String getStatus() {
		return status;
	}

	public int getSequence() {
		return sequence;
	}

	public float getPrice() {
		return price;
	}

	public int getQty() {
		return qty;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public List<SpareLangBean> getLangs() {
		return langs;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public void setLangs(List<SpareLangBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareItemResponse [itemid=");
		builder.append(itemid);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", price=");
		builder.append(price);
		builder.append(", qty=");
		builder.append(qty);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public SpareItemResponse(int itemid, int categoryid, String status, int sequence, float price, int qty,
			String creationtime) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.price = price;
		this.qty = qty;
		this.creationtime = creationtime;
	}

	public SpareItemResponse(int itemid, int categoryid, String status, int sequence, float price, int qty,
			String creationtime, List<ActivityPhotoBean> photolist, List<SpareLangBean> langs) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.price = price;
		this.qty = qty;
		this.creationtime = creationtime;
		this.photolist = photolist;
		this.langs = langs;
	}

}
