package com.sidc.blackcore.api.sits.spare.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;

public class SpareCategoryInsertRequest implements Serializable {
	private static final long serialVersionUID = 6827188200790723597L;
	private String token;
	private int status;
	private int sequence;
	private List<SpareLangBean> list = new ArrayList<SpareLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();

	public String getToken() {
		return token;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareCategoryInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", list=");
		builder.append(list);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public SpareCategoryInsertRequest(String token, int status, int sequence, List<SpareLangBean> list,
			List<ActivityPhotoUploadBean> photolist) {
		super();
		this.token = token;
		this.status = status;
		this.sequence = sequence;
		this.list = list;
		this.photolist = photolist;
	}

}
