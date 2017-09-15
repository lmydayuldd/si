package com.sidc.blackcore.api.mobile.laundry.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryWashTypeBean;

public class LaundryItemResponse implements Serializable {
	private static final long serialVersionUID = -7060667702995309050L;
	private int typeid;
	private int status;
	private List<LaundryLangBean> langs = new ArrayList<LaundryLangBean>();
	private List<LaundryReturnTypeResponse> returntypelist = new ArrayList<LaundryReturnTypeResponse>();
	private List<LaundryWashTypeBean> washtypelist = new ArrayList<LaundryWashTypeBean>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();

	public int getTypeid() {
		return typeid;
	}

	public int getStatus() {
		return status;
	}

	public List<LaundryLangBean> getLangs() {
		return langs;
	}

	public List<LaundryReturnTypeResponse> getReturntypelist() {
		return returntypelist;
	}

	public List<LaundryWashTypeBean> getWashtypelist() {
		return washtypelist;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryItemResponse [typeid=");
		builder.append(typeid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langs=");
		builder.append(langs);
		builder.append(", returntypelist=");
		builder.append(returntypelist);
		builder.append(", washtypelist=");
		builder.append(washtypelist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public LaundryItemResponse(int typeid, int status, List<LaundryLangBean> langs,
			List<LaundryReturnTypeResponse> returntypelist, List<LaundryWashTypeBean> washtypelist,
			List<ActivityPhotoBean> photolist) {
		super();
		this.typeid = typeid;
		this.status = status;
		this.langs = langs;
		this.returntypelist = returntypelist;
		this.washtypelist = washtypelist;
		this.photolist = photolist;
	}

	public LaundryItemResponse(int typeid, int status, List<LaundryLangBean> langs,
			List<LaundryReturnTypeResponse> returntypelist, List<LaundryWashTypeBean> washtypelist) {
		super();
		this.typeid = typeid;
		this.status = status;
		this.langs = langs;
		this.returntypelist = returntypelist;
		this.washtypelist = washtypelist;
	}

}
