package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class RoomServiceSetItemInfoBean implements Serializable {
	private static final long serialVersionUID = -3202876717291097960L;
	private int itemid;
	private int status;
	private int sequence;
	private List<RoomServiceLangBean> langs = new ArrayList<RoomServiceLangBean>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();

	public int getItemid() {
		return itemid;
	}

	public int getStatus() {
		return status;
	}

	public int getSequence() {
		return sequence;
	}

	public List<RoomServiceLangBean> getLangs() {
		return langs;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public void setLangs(List<RoomServiceLangBean> langs) {
		this.langs = langs;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceSetItemInfoBean [itemid=");
		builder.append(itemid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", langs=");
		builder.append(langs);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceSetItemInfoBean(int itemid, int status, int sequence) {
		super();
		this.itemid = itemid;
		this.status = status;
		this.sequence = sequence;
	}

}
