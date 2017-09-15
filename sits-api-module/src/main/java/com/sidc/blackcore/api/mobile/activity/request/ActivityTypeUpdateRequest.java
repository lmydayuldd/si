package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityTypeBean;

public class ActivityTypeUpdateRequest implements Serializable {
	private static final long serialVersionUID = 4158345995454906755L;
	private String token;
	private int id;
	private int status;
	private List<ActivityTypeBean> list = new ArrayList<ActivityTypeBean>();

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public List<ActivityTypeBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityTypeUpdateRequest [token=");
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

	public ActivityTypeUpdateRequest(String token, int id, int status, List<ActivityTypeBean> list) {
		super();
		this.token = token;
		this.id = id;
		this.status = status;
		this.list = list;
	}

}
