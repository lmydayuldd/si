package com.sidc.blackcore.api.mobile.activity.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityTypeBean;

public class ActivityTypeResponse implements Serializable {
	private static final long serialVersionUID = -8080637251770407058L;
	private int typeid;
	private List<ActivityTypeBean> langs = new ArrayList<ActivityTypeBean>();

	public int getTypeid() {
		return typeid;
	}

	public List<ActivityTypeBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityTypeResponse [typeid=");
		builder.append(typeid);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public ActivityTypeResponse(int typeid, List<ActivityTypeBean> langs) {
		super();
		this.typeid = typeid;
		this.langs = langs;
	}

}
