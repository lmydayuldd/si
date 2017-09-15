package com.sidc.blackcore.api.sits.shop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingCategoryResponse implements Serializable {
	private static final long serialVersionUID = 8785236451675341626L;
	private int categoryid;
	private int status;
	private int referid;
	private int sequence;
	private String creationtime;
	private List<ShoppingCategoryResponse> sublist = new ArrayList<ShoppingCategoryResponse>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();
	private List<ShopLangBean> langs = new ArrayList<ShopLangBean>();

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

	public String getCreationtime() {
		return creationtime;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public List<ShopLangBean> getLangs() {
		return langs;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public void setLangs(List<ShopLangBean> langs) {
		this.langs = langs;
	}

	public List<ShoppingCategoryResponse> getSublist() {
		return sublist;
	}

	public void setSublist(List<ShoppingCategoryResponse> sublist) {
		this.sublist = sublist;
	}

	public ShoppingCategoryResponse(int categoryid, int status, int referid, int sequence, String creationtime,
			List<ActivityPhotoBean> photolist, List<ShopLangBean> langs) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.referid = referid;
		this.sequence = sequence;
		this.creationtime = creationtime;
		this.photolist = photolist;
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingCategoryResponse [categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", referid=");
		builder.append(referid);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", sublist=");
		builder.append(sublist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public ShoppingCategoryResponse(int categoryid, int status, int referid, int sequence, String creationtime) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.referid = referid;
		this.sequence = sequence;
		this.creationtime = creationtime;
	}

}
