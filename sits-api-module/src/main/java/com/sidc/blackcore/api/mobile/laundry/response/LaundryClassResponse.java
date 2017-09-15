package com.sidc.blackcore.api.mobile.laundry.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryClassResponse implements Serializable {
	private static final long serialVersionUID = -7735334105413756076L;
	private int classid;
	private int status;
	private List<LaundryLangBean> langs = new ArrayList<LaundryLangBean>();

	public int getClassid() {
		return classid;
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
		builder.append("LaundryClassResponse [classid=");
		builder.append(classid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public LaundryClassResponse(int classid, int status, List<LaundryLangBean> langs) {
		super();
		this.classid = classid;
		this.status = status;
		this.langs = langs;
	}

}
