package com.sidc.blackcore.api.mobile.laundry.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryWashTypeResponse implements Serializable {
	private static final long serialVersionUID = 2980416640930194832L;
	private int washtypeid;
	private int status;
	private List<LaundryLangBean> langs = new ArrayList<LaundryLangBean>();

	public int getWashtypeid() {
		return washtypeid;
	}

	public int getStatus() {
		return status;
	}

	public List<LaundryLangBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryWashTypeResponse [washtypeid=");
		builder.append(washtypeid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public LaundryWashTypeResponse(int washtypeid, int status, List<LaundryLangBean> langs) {
		super();
		this.washtypeid = washtypeid;
		this.status = status;
		this.langs = langs;
	}

}
