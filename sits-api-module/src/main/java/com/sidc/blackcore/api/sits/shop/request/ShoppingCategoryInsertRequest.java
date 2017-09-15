package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingCategoryInsertRequest implements Serializable {
	private static final long serialVersionUID = -1103511311043869292L;
	private String token;
	private int status;
	private int referid;
	private int sequence;
	private List<ShopLangBean> list = new ArrayList<ShopLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();

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

	public String getToken() {
		return token;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	public ShoppingCategoryInsertRequest(String token, int status, int referid, int sequence, List<ShopLangBean> list,
			List<ActivityPhotoUploadBean> photolist) {
		super();
		this.token = token;
		this.status = status;
		this.referid = referid;
		this.sequence = sequence;
		this.list = list;
		this.photolist = photolist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingCategoryInsertRequest [token=");
		builder.append(token);
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
		builder.append("]");
		return builder.toString();
	}
}
