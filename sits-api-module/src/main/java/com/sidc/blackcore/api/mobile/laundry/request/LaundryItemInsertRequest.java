package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToReturnTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToWashTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryItemInsertRequest implements Serializable {
	private static final long serialVersionUID = 5173423014284033951L;
	private String token;
	private int status;
	private int type;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
	private List<LaundryItemToWashTypeBean> washtypelist = new ArrayList<LaundryItemToWashTypeBean>();
	private List<LaundryItemToReturnTypeBean> returntypelist = new ArrayList<LaundryItemToReturnTypeBean>();
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

	public List<LaundryItemToWashTypeBean> getWashtypelist() {
		return washtypelist;
	}

	public List<LaundryItemToReturnTypeBean> getReturntypelist() {
		return returntypelist;
	}

	public void setReturntypelist(List<LaundryItemToReturnTypeBean> returntypelist) {
		this.returntypelist = returntypelist;
	}

	public int getType() {
		return type;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryItemInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", list=");
		builder.append(list);
		builder.append(", washtypelist=");
		builder.append(washtypelist);
		builder.append(", returntypelist=");
		builder.append(returntypelist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public LaundryItemInsertRequest(String token, int status, int type, List<LaundryLangBean> list,
			List<LaundryItemToWashTypeBean> washtypelist, List<LaundryItemToReturnTypeBean> returntypelist,
			List<ActivityPhotoUploadBean> photolist) {
		super();
		this.token = token;
		this.status = status;
		this.type = type;
		this.list = list;
		this.washtypelist = washtypelist;
		this.returntypelist = returntypelist;
		this.photolist = photolist;
	}

	public LaundryItemInsertRequest(String token, int status, int type, List<LaundryLangBean> list,
			List<LaundryItemToWashTypeBean> washtypelist, List<ActivityPhotoUploadBean> photolist) {
		super();
		this.token = token;
		this.status = status;
		this.type = type;
		this.list = list;
		this.washtypelist = washtypelist;
		this.photolist = photolist;
	}

}
