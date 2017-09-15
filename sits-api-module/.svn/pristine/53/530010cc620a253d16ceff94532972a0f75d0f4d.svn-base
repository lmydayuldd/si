package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryClassInsertRequest implements Serializable {

	private static final long serialVersionUID = -3460282788674592682L;
	private String token;
	private int status;
	private float servicecharge;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();

	public String getToken() {
		return token;
	}

	public int getStatus() {
		return status;
	}

	public float getServicecharge() {
		return servicecharge;
	}

	public List<LaundryLangBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryClassInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", servicecharge=");
		builder.append(servicecharge);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public LaundryClassInsertRequest(String token, int status, float servicecharge, List<LaundryLangBean> list) {
		super();
		this.token = token;
		this.status = status;
		this.servicecharge = servicecharge;
		this.list = list;
	}

}
