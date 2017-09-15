package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetBean;

public class RoomServiceItemUpdateRequest implements Serializable {
	private static final long serialVersionUID = -8499568326414311771L;
	private String token;
	private int itemid;
	private int categoryid;
	private int sequence;
	private int status;
	private float price;
	private String type;
	private List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
	private List<RoomServiceSetBean> setlist = new ArrayList<RoomServiceSetBean>();
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

	public int getStatus() {
		return status;
	}

	public float getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public List<RoomServiceLangBean> getList() {
		return list;
	}

	public List<RoomServiceSetBean> getSetlist() {
		return setlist;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getSequence() {
		return sequence;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	public boolean isPhotoupdate() {
		return photoupdate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceItemUpdateRequest [token=");
		builder.append(token);
		builder.append(", itemid=");
		builder.append(itemid);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", status=");
		builder.append(status);
		builder.append(", price=");
		builder.append(price);
		builder.append(", type=");
		builder.append(type);
		builder.append(", list=");
		builder.append(list);
		builder.append(", setlist=");
		builder.append(setlist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", photoupdate=");
		builder.append(photoupdate);
		builder.append("]");
		return builder.toString();
	}

	public void setSetlist(List<RoomServiceSetBean> setlist) {
		this.setlist = setlist;
	}

	public RoomServiceItemUpdateRequest(String token, int itemid, int categoryid, int sequence, int status, float price,
			String type, List<RoomServiceLangBean> list, List<RoomServiceSetBean> setlist,
			List<ActivityPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.token = token;
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.sequence = sequence;
		this.status = status;
		this.price = price;
		this.type = type;
		this.list = list;
		this.setlist = setlist;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

	public RoomServiceItemUpdateRequest(String token, int itemid, int categoryid, int sequence, int status, float price,
			String type, List<RoomServiceLangBean> list, List<RoomServiceSetBean> setlist, boolean photoupdate) {
		super();
		this.token = token;
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.sequence = sequence;
		this.status = status;
		this.price = price;
		this.type = type;
		this.list = list;
		this.setlist = setlist;
		this.photoupdate = photoupdate;
	}

}
