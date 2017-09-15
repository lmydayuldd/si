package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeLangBean;

public class ActivityFeeUpdateRequest implements Serializable {
	private static final long serialVersionUID = -8353566993037749493L;
	private String token;
	private int id;
	private int status;
	private List<ActivityFeeLangBean> list = new ArrayList<ActivityFeeLangBean>();

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public List<ActivityFeeLangBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityFeeUpdateRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public ActivityFeeUpdateRequest(String token, int id, int status, List<ActivityFeeLangBean> list) {
		super();
		this.token = token;
		this.id = id;
		this.status = status;
		this.list = list;
	}

}
