package com.sidc.rcu.hmi.modesetting.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.modesetting.bean.RcuModeCatalogueBean;

public class ModeSettingInsertRequest implements Serializable {

	private static final long serialVersionUID = -4847700015979605108L;
	private String modeName;
	private int groupId;
	private List<RcuModeCatalogueBean> mode = new ArrayList<RcuModeCatalogueBean>();

	public String getModeName() {
		return modeName;
	}

	public int getGroupId() {
		return groupId;
	}

	public List<RcuModeCatalogueBean> getMode() {
		return mode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeSettingInsertRequest [modeName=");
		builder.append(modeName);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", mode=");
		builder.append(mode);
		builder.append("]");
		return builder.toString();
	}

	public ModeSettingInsertRequest(String modeName, int groupId, List<RcuModeCatalogueBean> mode) {
		super();
		this.modeName = modeName;
		this.groupId = groupId;
		this.mode = mode;
	}

}
