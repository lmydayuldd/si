package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;

public class RoomServiceCategoryUpdateRequest implements Serializable {
	private static final long serialVersionUID = 5860733854574896324L;
	private String token;
	private int id;
	private int referid;
	private int status;
	private String startime;
	private String endtime;
	private int sequence;
	private List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();
	private boolean photoupdate;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public int getReferid() {
		return referid;
	}

	public int getStatus() {
		return status;
	}

	public String getStartime() {
		return startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public List<RoomServiceLangBean> getList() {
		return list;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
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
		builder.append("RoomServiceCategoryUpdateRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append(", referid=");
		builder.append(referid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", startime=");
		builder.append(startime);
		builder.append(", endtime=");
		builder.append(endtime);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", list=");
		builder.append(list);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", photoupdate=");
		builder.append(photoupdate);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceCategoryUpdateRequest(String token, int id, int referid, int status, String startime,
			String endtime, int sequence, List<RoomServiceLangBean> list, List<ActivityPhotoUploadBean> photolist,
			boolean photoupdate) {
		super();
		this.token = token;
		this.id = id;
		this.referid = referid;
		this.status = status;
		this.startime = startime;
		this.endtime = endtime;
		this.sequence = sequence;
		this.list = list;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

	public RoomServiceCategoryUpdateRequest(String token, int id, int referid, int status, String startime,
			String endtime, int sequence, List<RoomServiceLangBean> list, boolean photoupdate) {
		super();
		this.token = token;
		this.id = id;
		this.referid = referid;
		this.status = status;
		this.startime = startime;
		this.endtime = endtime;
		this.sequence = sequence;
		this.list = list;
		this.photoupdate = photoupdate;
	}

}
