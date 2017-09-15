package com.sidc.blackcore.api.sits.spare.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;

public class SpareItemUpdateRequest implements Serializable {
	private static final long serialVersionUID = 87913191058232758L;
	private String token;
	private int itemid;
	private int categoryid;
	private String status;
	private int sequence;
	private float price;
	private int qty;
	private List<SpareLangBean> list = new ArrayList<SpareLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();
	private boolean photoupdate;

	public String getToken() {
		return token;
	}

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

	public List<SpareLangBean> getList() {
		return list;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public boolean isPhotoupdate() {
		return photoupdate;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareItemUpdateRequest [token=");
		builder.append(token);
		builder.append(", itemid=");
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
		builder.append(", list=");
		builder.append(list);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", photoupdate=");
		builder.append(photoupdate);
		builder.append("]");
		return builder.toString();
	}

	public SpareItemUpdateRequest(String token, int itemid, int categoryid, String status, int sequence, float price,
			int qty, List<SpareLangBean> list, List<ActivityPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.token = token;
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.price = price;
		this.qty = qty;
		this.list = list;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

}
