package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryWashTypeUpdateRequest implements Serializable {
	private static final long serialVersionUID = -2735830066628325733L;
	private String token;
	private int washtypeid;
	private int status;
	private List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();

	public String getToken() {
		return token;
	}

	public int getWashtypeid() {
		return washtypeid;
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
		builder.append("LaundryWashTypeUpdateRequest [token=");
		builder.append(token);
		builder.append(", washtypeid=");
		builder.append(washtypeid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public LaundryWashTypeUpdateRequest(String token, int washtypeid, int status, List<LaundryLangBean> list) {
		super();
		this.token = token;
		this.washtypeid = washtypeid;
		this.status = status;
		this.list = list;
	}

}
