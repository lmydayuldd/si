package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryTypeInsertRequest implements Serializable {
	private static final long serialVersionUID = 6078292050919315637L;
	private String token;
	private int status;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();

	public String getToken() {
		return token;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryTypeInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public LaundryTypeInsertRequest(String token, int status, List<LaundryLangBean> list,
			List<ActivityPhotoUploadBean> photolist) {
		super();
		this.token = token;
		this.status = status;
		this.list = list;
		this.photolist = photolist;
	}

}
