package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryReturnTypeUpdateRequest implements Serializable {
	private static final long serialVersionUID = -1924159235969288218L;
	private String token;
	private int returntypeid;
	private int status;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();

	public String getToken() {
		return token;
	}

	public int getReturntypeid() {
		return returntypeid;
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
		builder.append("LaundryReturnTypeUpdateRequest [token=");
		builder.append(token);
		builder.append(", returntypeid=");
		builder.append(returntypeid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public LaundryReturnTypeUpdateRequest(String token, int returntypeid, int status, List<LaundryLangBean> list) {
		super();
		this.token = token;
		this.returntypeid = returntypeid;
		this.status = status;
		this.list = list;
	}

}
