package com.sidc.blackcore.api.mobile.activity.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeLangBean;

public class ActivityFeeResponse implements Serializable {
	private static final long serialVersionUID = -4583714722711740364L;
	private int feeid;
	private List<ActivityFeeLangBean> langs = new ArrayList<ActivityFeeLangBean>();

	public int getFeeid() {
		return feeid;
	}

	public List<ActivityFeeLangBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityFeeResponse [feeid=");
		builder.append(feeid);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public ActivityFeeResponse(int feeid, List<ActivityFeeLangBean> langs) {
		super();
		this.feeid = feeid;
		this.langs = langs;
	}

}
