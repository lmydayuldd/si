package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityTypeBean;

public class ActivityTypeInsertRequest implements Serializable {
	private static final long serialVersionUID = 7455053495616253317L;
	private String token;
	private int status;
	private List<ActivityTypeBean> list = new ArrayList<ActivityTypeBean>();

	public String getToken() {
		return token;
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
		builder.append("ActivityTypeInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public ActivityTypeInsertRequest(String token, int status, List<ActivityTypeBean> list) {
		super();
		this.token = token;
		this.status = status;
		this.list = list;
	}

}
