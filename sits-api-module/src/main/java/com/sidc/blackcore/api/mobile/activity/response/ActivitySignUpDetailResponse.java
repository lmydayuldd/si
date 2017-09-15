package com.sidc.blackcore.api.mobile.activity.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivitySignUpDetailBean;

public class ActivitySignUpDetailResponse implements Serializable {
	private static final long serialVersionUID = -1011971670826098811L;
	private List<ActivitySignUpDetailBean> list = new ArrayList<ActivitySignUpDetailBean>();

	public List<ActivitySignUpDetailBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivitySignUpDetailResponse [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public ActivitySignUpDetailResponse(List<ActivitySignUpDetailBean> list) {
		super();
		this.list = list;
	}

}
