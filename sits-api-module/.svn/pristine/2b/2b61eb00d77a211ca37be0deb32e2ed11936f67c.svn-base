package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceBean implements Serializable {
	private static final long serialVersionUID = -707956328440877965L;
	private int categoryid;
	private int status;
	private String servicestartime;
	private String serviceendtime;
	private String name;
	private String description;
	private String creationtime;
	private List<RoomServiceBean> sublist = new ArrayList<RoomServiceBean>();
	private List<RoomServiceItemBean> itemlist = new ArrayList<RoomServiceItemBean>();

	public int getCategoryid() {
		return categoryid;
	}

	public int getStatus() {
		return status;
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

	public List<RoomServiceBean> getSublist() {
		return sublist;
	}

	public void setSublist(List<RoomServiceBean> sublist) {
		this.sublist = sublist;
	}

	public List<RoomServiceItemBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<RoomServiceItemBean> itemlist) {
		this.itemlist = itemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceBean [categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
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
		builder.append("]");
		return builder.toString();
	}

	

}
