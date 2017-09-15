package com.sidc.blackcore.api.mobile.activity.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityFeeBean implements Serializable {

	private static final long serialVersionUID = -9081887149643494108L;
	private int feeid;
	private List<ActivityFeeLangBean> langlist = new ArrayList<ActivityFeeLangBean>();

	public int getFeeid() {
		return feeid;
	}

	public List<ActivityFeeLangBean> getLanglist() {
		return langlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityFeeBean [feeid=");
		builder.append(feeid);
		builder.append(", langlist=");
		builder.append(langlist);
		builder.append("]");
		return builder.toString();
	}

	public ActivityFeeBean(int feeid, List<ActivityFeeLangBean> langlist) {
		super();
		this.feeid = feeid;
		this.langlist = langlist;
	}

}
