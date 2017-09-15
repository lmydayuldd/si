package com.sidc.blackcore.api.sits.spare.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;

public class SpareCategoryUpdateRequest implements Serializable {
	private static final long serialVersionUID = 52376463435075874L;
	private String token;
	private int categoryid;
	private int status;
	private int sequence;
	private List<SpareLangBean> list = new ArrayList<SpareLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();
	private boolean photoupdate;

	public String getToken() {
		return token;
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

	public List<SpareLangBean> getList() {
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
		builder.append("SpareCategoryUpdateRequest [token=");
		builder.append(token);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
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

	public SpareCategoryUpdateRequest(String token, int categoryid, int status, int sequence, List<SpareLangBean> list,
			List<ActivityPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.token = token;
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.list = list;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

}