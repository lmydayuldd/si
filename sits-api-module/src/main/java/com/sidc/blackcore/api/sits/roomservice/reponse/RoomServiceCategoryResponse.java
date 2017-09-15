package com.sidc.blackcore.api.sits.roomservice.reponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;

public class RoomServiceCategoryResponse implements Serializable {
	private static final long serialVersionUID = 1901077083396031954L;
	private int categoryid;
	private int status;
	private int referid;
	private int sequence;
	private String servicestartime;
	private String serviceendtime;
	private String creationtime;
	private List<RoomServiceCategoryResponse> sublist = new ArrayList<RoomServiceCategoryResponse>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();
	private List<RoomServiceLangBean> langs = new ArrayList<RoomServiceLangBean>();

	public int getCategoryid() {
		return categoryid;
	}

	public int getStatus() {
		return status;
	}

	public int getReferid() {
		return referid;
	}

	public int getSequence() {
		return sequence;
	}

	public String getServicestartime() {
		return servicestartime;
	}

	public String getServiceendtime() {
		return serviceendtime;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public List<RoomServiceCategoryResponse> getSublist() {
		return sublist;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public List<RoomServiceLangBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceCategoryInfoBean [categoryid=");
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
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", sublist=");
		builder.append(sublist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceCategoryResponse(int categoryid, int status, int referid, int sequence, String servicestartime,
			String serviceendtime, String creationtime) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.referid = referid;
		this.sequence = sequence;
		this.servicestartime = servicestartime;
		this.serviceendtime = serviceendtime;
		this.creationtime = creationtime;
	}

	public void setSublist(List<RoomServiceCategoryResponse> sublist) {
		this.sublist = sublist;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public void setLangs(List<RoomServiceLangBean> langs) {
		this.langs = langs;
	}

}
