package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToReturnTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToWashTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryItemUpdateRequest implements Serializable {
	private static final long serialVersionUID = 1317483391021079241L;
	private String token;
	private int itemid;
	private int status;
	private int type;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
	private List<LaundryItemToWashTypeBean> washtypelist = new ArrayList<LaundryItemToWashTypeBean>();
	private List<LaundryItemToReturnTypeBean> returntypelist = new ArrayList<LaundryItemToReturnTypeBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();
	private boolean photoupdate;

	public String getToken() {
		return token;
	}

	public int getItemid() {
		return itemid;
	}

	public int getStatus() {
		return status;
	}

	public int getType() {
		return type;
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

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isPhotoupdate() {
		return photoupdate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryItemUpdateRequest [token=");
		builder.append(token);
		builder.append(", itemid=");
		builder.append(itemid);
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
		builder.append(", photoupdate=");
		builder.append(photoupdate);
		builder.append("]");
		return builder.toString();
	}

	public LaundryItemUpdateRequest(String token, int itemid, int status, int type, List<LaundryLangBean> list,
			List<LaundryItemToWashTypeBean> washtypelist, List<LaundryItemToReturnTypeBean> returntypelist,
			List<ActivityPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.token = token;
		this.itemid = itemid;
		this.status = status;
		this.type = type;
		this.list = list;
		this.washtypelist = washtypelist;
		this.returntypelist = returntypelist;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

	public LaundryItemUpdateRequest(String token, int itemid, int status, int type, List<LaundryLangBean> list,
			List<LaundryItemToWashTypeBean> washtypelist, List<ActivityPhotoUploadBean> photolist,
			boolean photoupdate) {
		super();
		this.token = token;
		this.itemid = itemid;
		this.status = status;
		this.type = type;
		this.list = list;
		this.washtypelist = washtypelist;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

}
