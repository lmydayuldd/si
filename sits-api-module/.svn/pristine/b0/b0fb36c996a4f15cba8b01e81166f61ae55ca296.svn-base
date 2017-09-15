package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class RoomServiceItemBean implements Serializable {
	private static final long serialVersionUID = -4962579587150426506L;
	private int itemid;
	private int status;
	private int sequence;
	private String type;
	private String name;
	private String description;
	private float price;
	private List<RoomServiceSetCategoryBean> setlist = new ArrayList<RoomServiceSetCategoryBean>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();

	public int getItemid() {
		return itemid;
	}

	public int getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<RoomServiceSetCategoryBean> getSetlist() {
		return setlist;
	}

	public void setSetlist(List<RoomServiceSetCategoryBean> setlist) {
		this.setlist = setlist;
	}

	public float getPrice() {
		return price;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public int getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceItemBean [itemid=");
		builder.append(itemid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", price=");
		builder.append(price);
		builder.append(", setlist=");
		builder.append(setlist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceItemBean(int itemid, int status, int sequence, String type, String name, String description,
			float price, List<RoomServiceSetCategoryBean> setlist) {
		super();
		this.itemid = itemid;
		this.status = status;
		this.sequence = sequence;
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.setlist = setlist;
	}

	public RoomServiceItemBean(int itemid, int status, int sequence, String type, String name, String description,
			float price) {
		super();
		this.itemid = itemid;
		this.status = status;
		this.sequence = sequence;
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
	}

}
