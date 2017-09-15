package com.sidc.blackcore.api.sits.spare.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;

public class SpareCategoryResponse implements Serializable {
	private static final long serialVersionUID = -1909793505987226807L;
	private int categoryid;
	private int status;
	private int sequence;
	private String creationtime;
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();
	private List<SpareLangBean> langs = new ArrayList<SpareLangBean>();

	public int getCategoryid() {
		return categoryid;
	}

	public int getStatus() {
		return status;
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

	public List<SpareLangBean> getLangs() {
		return langs;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public void setLangs(List<SpareLangBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareCategoryResponse [categoryid=");
		builder.append(categoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public SpareCategoryResponse(int categoryid, int status, int sequence, String creationtime) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.creationtime = creationtime;
	}

	public SpareCategoryResponse(int categoryid, int status, int sequence, String creationtime,
			List<ActivityPhotoBean> photolist, List<SpareLangBean> langs) {
		super();
		this.categoryid = categoryid;
		this.status = status;
		this.sequence = sequence;
		this.creationtime = creationtime;
		this.photolist = photolist;
		this.langs = langs;
	}

}
