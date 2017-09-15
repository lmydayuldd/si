package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeLangBean;

public class ActivityFeeInsertRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3307148673002757670L;
	private String token;
	private int status;
	private List<ActivityFeeLangBean> list = new ArrayList<ActivityFeeLangBean>();

	public String getToken() {
		return token;
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
		builder.append("ActivityFeeInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public ActivityFeeInsertRequest(String token, int status, List<ActivityFeeLangBean> list) {
		super();
		this.token = token;
		this.status = status;
		this.list = list;
	}

}
