package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class FcmNewslettersMessageBean implements Serializable {
	private static final long serialVersionUID = -3392125468574943522L;
	private String newsletterid;
	private String description;
	private String creationdate;
	private List<ActivityPhotoBean> imagelist = new ArrayList<ActivityPhotoBean>();

	public String getNewsletterid() {
		return newsletterid;
	}

	public String getDescription() {
		return description;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public List<ActivityPhotoBean> getImagelist() {
		return imagelist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FcmNewslettersMessageBean [newsletterid=");
		builder.append(newsletterid);
		builder.append(", description=");
		builder.append(description);
		builder.append(", creationdate=");
		builder.append(creationdate);
		builder.append(", imagelist=");
		builder.append(imagelist);
		builder.append("]");
		return builder.toString();
	}

	public FcmNewslettersMessageBean(String newsletterid, String description, String creationdate,
			List<ActivityPhotoBean> imagelist) {
		super();
		this.newsletterid = newsletterid;
		this.description = description;
		this.creationdate = creationdate;
		this.imagelist = imagelist;
	}

}
