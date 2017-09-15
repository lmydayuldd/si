package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class NewslettersBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3705790310558129330L;
	private String newsletterid;
	private String title;
	private String description;
	private String creationdate;
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();

	public String getNewsletterid() {
		return newsletterid;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getCreationdate() {
		return creationdate;
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
		builder.append("NewslettersBean [newsletterid=");
		builder.append(newsletterid);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", creationdate=");
		builder.append(creationdate);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public NewslettersBean(String newsletterid, String title, String description, String creationdate) {
		super();
		this.newsletterid = newsletterid;
		this.title = title;
		this.description = description;
		this.creationdate = creationdate;
	}

}
