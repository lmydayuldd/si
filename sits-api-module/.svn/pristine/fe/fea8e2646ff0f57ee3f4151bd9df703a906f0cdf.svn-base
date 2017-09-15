package com.sidc.blackcore.api.mobile.laundry.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryReturnTypeResponse implements Serializable {
	private static final long serialVersionUID = 1934803549148476857L;
	private int returntypeid;
	private int status;
	private List<LaundryLangBean> langs = new ArrayList<LaundryLangBean>();

	public int getReturntypeid() {
		return returntypeid;
	}

	public List<LaundryLangBean> getLangs() {
		return langs;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryReturnTypeResponse [returntypeid=");
		builder.append(returntypeid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public LaundryReturnTypeResponse(int returntypeid, int status, List<LaundryLangBean> langs) {
		super();
		this.returntypeid = returntypeid;
		this.status = status;
		this.langs = langs;
	}
}
