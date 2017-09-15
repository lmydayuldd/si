package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class RoomServiceItemSetBean implements Serializable {
	private static final long serialVersionUID = 6385403408662615145L;
	private int itemid;
	private int categoryid;
	private int status;
	private int sequence;
	private String creationtime;
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

	public String getCreationtime() {
		return creationtime;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public List<RoomServiceLangBean> getLangs() {
		return langs;
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
		builder.append("RoomServiceItemSetBean [itemid=");
		builder.append(itemid);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceItemSetBean(int itemid, int categoryid, int status, int sequence, String creationtime,
			List<ActivityPhotoBean> photolist, List<RoomServiceLangBean> langs) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.creationtime = creationtime;
		this.photolist = photolist;
		this.langs = langs;
	}

	public RoomServiceItemSetBean(int itemid, int categoryid, int status, int sequence, String creationtime) {
		super();
		this.itemid = itemid;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.creationtime = creationtime;
	}

}
