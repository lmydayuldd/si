package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryTypeUpdateRequest implements Serializable {
	private static final long serialVersionUID = 4907457752940581263L;
	private String token;
	private int typeid;
	private int status;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();
	private boolean photoupdate;

	public String getToken() {
		return token;
	}

	public int getTypeid() {
		return typeid;
	}

	public int getStatus() {
		return status;
	}

	public List<LaundryLangBean> getList() {
		return list;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
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
		builder.append("LaundryTypeUpdateRequest [token=");
		builder.append(token);
		builder.append(", typeid=");
		builder.append(typeid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", photoupdate=");
		builder.append(photoupdate);
		builder.append("]");
		return builder.toString();
	}

	public LaundryTypeUpdateRequest(String token, int typeid, int status, List<LaundryLangBean> list,
			List<ActivityPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.token = token;
		this.typeid = typeid;
		this.status = status;
		this.list = list;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

}
