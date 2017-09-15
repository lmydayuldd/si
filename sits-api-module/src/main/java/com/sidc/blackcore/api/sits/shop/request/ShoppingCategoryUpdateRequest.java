package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingCategoryUpdateRequest implements Serializable {
	private static final long serialVersionUID = 7529910011732929468L;
	private String token;
	private int categoryid;
	private int status;
	private int referid;
	private int sequence;
	private List<ShopLangBean> list = new ArrayList<ShopLangBean>();
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

	public int getReferid() {
		return referid;
	}

	public int getSequence() {
		return sequence;
	}

	public List<ShopLangBean> getList() {
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
		builder.append("ShoppingCategoryUpdateRequest [token=");
		builder.append(token);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", referid=");
		builder.append(referid);
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

	public ShoppingCategoryUpdateRequest(String token, int categoryid, int status, int referid, int sequence,
			List<ShopLangBean> list, boolean photoupdate) {
		super();
		this.token = token;
		this.categoryid = categoryid;
		this.status = status;
		this.referid = referid;
		this.sequence = sequence;
		this.list = list;
		this.photoupdate = photoupdate;
	}

	public ShoppingCategoryUpdateRequest(String token, int categoryid, int status, int referid, int sequence,
			List<ShopLangBean> list, List<ActivityPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.token = token;
		this.categoryid = categoryid;
		this.status = status;
		this.referid = referid;
		this.sequence = sequence;
		this.list = list;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

}