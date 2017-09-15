package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class RoomServiceCategoryBean implements Serializable {
	private static final long serialVersionUID = 3647302966752400572L;
	private int categoryid;
	private int status;
	private int referid;
	private int sequence;
	private String servicestartime;
	private String serviceendtime;
	private String name;
	private String description;
	private String creationtime;
	private List<RoomServiceCategoryBean> sublist = new ArrayList<RoomServiceCategoryBean>();
	private List<RoomServiceItemBean> itemlist = new ArrayList<RoomServiceItemBean>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();

	public int getCategoryid() {
		return categoryid;
	}

	public int getStatus() {
		return status;
	}

	public int getReferid() {
		return referid;
	}

	public void setReferid(int referid) {
		this.referid = referid;
	}

	public String getServicestartime() {
		return servicestartime;
	}

	public String getServiceendtime() {
		return serviceendtime;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public List<RoomServiceCategoryBean> getSublist() {
		return sublist;
	}

	public void setSublist(List<RoomServiceCategoryBean> sublist) {
		this.sublist = sublist;
	}

	public List<RoomServiceItemBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<RoomServiceItemBean> itemlist) {
		this.itemlist = itemlist;
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
		builder.append("RoomServiceCategoryBean [categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", referid=");
		builder.append(referid);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", servicestartime=");
		builder.append(servicestartime);
		builder.append(", serviceendtime=");
		builder.append(serviceendtime);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", sublist=");
		builder.append(sublist);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceCategoryBean(int categoryid, int status, int referid, int sequence, String servicestartime,
			String serviceendtime, String name, String description, String creationtime) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.referid = referid;
		this.sequence = sequence;
		this.servicestartime = servicestartime;
		this.serviceendtime = serviceendtime;
		this.name = name;
		this.description = description;
		this.creationtime = creationtime;
	}

}
