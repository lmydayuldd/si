package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryReturnTypeInsertRequest implements Serializable {
	private static final long serialVersionUID = 2423272255840985854L;
	private String token;
	private int status;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();

	public String getToken() {
		return token;
	}

	public int getStatus() {
		return status;
	}

	public List<LaundryLangBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryReturnTypeInsertRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public LaundryReturnTypeInsertRequest(String token, int status, List<LaundryLangBean> list) {
		super();
		this.token = token;
		this.status = status;
		this.list = list;
	}

}
