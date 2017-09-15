package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FcmNewslettersObjectBean implements Serializable {
	private static final long serialVersionUID = -8018691185057127866L;
	private List<FcmNewslettersMessageBean> newsletterinfo = new ArrayList<FcmNewslettersMessageBean>();

	public List<FcmNewslettersMessageBean> getNewsletterinfo() {
		return newsletterinfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FcmNewslettersObjectBean [newsletterinfo=");
		builder.append(newsletterinfo);
		builder.append("]");
		return builder.toString();
	}

	public FcmNewslettersObjectBean(List<FcmNewslettersMessageBean> newsletterinfo) {
		super();
		this.newsletterinfo = newsletterinfo;
	}

}
