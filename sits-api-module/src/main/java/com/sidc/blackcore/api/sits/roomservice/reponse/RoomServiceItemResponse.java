package com.sidc.blackcore.api.sits.roomservice.reponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;

public class RoomServiceItemResponse implements Serializable {
	private static final long serialVersionUID = 1894411247091138838L;
	private int itemid;
	private int categoryid;
	private int status;
	private int sequence;
	private float price;
	private String type;
	private String creationtime;
	private List<RoomServiceItemCategoryBean> setlist = new ArrayList<RoomServiceItemCategoryBean>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();
	private List<RoomServiceLangBean> langs = new ArrayList<RoomServiceLangBean>();

	public int getItemid() {
		return itemid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public int getStatus() {
		return status;
	}

	public int getSequence() {
		return sequence;
	}

	public float getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public List<RoomServiceItemCategoryBean> getSetlist() {
		return setlist;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public List<RoomServiceLangBean> getLangs() {
		return langs;
	}

	public void setSetlist(List<RoomServiceItemCategoryBean> setlist) {
		this.setlist = setlist;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public void setLangs(List<RoomServiceLangBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceItemResponse [itemid=");
		builder.append(itemid);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", price=");
		builder.append(price);
		builder.append(", type=");
		builder.append(type);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", setlist=");
		builder.append(setlist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceItemResponse(int itemid, int categoryid, int status, int sequence, float price, String type,
			String creationtime) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.price = price;
		this.type = type;
		this.creationtime = creationtime;
	}

	public RoomServiceItemResponse(int itemid, int categoryid, int status, int sequence, float price, String type,
			String creationtime, List<RoomServiceItemCategoryBean> setlist, List<ActivityPhotoBean> photolist,
			List<RoomServiceLangBean> langs) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.price = price;
		this.type = type;
		this.creationtime = creationtime;
		this.setlist = setlist;
		this.photolist = photolist;
		this.langs = langs;
	}
}
