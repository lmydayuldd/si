package com.sidc.blackcore.api.ra.rcumodesetting.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuModeSettingModeBean;

public class RcuModeSettingInsertRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -750542737392841952L;
	private String modeName;
	private int groupId;
	private List<RcuModeSettingModeBean> mode = new ArrayList<RcuModeSettingModeBean>();

	public String getModeName() {
		return modeName;
	}

	public int getGroupId() {
		return groupId;
	}

	public List<RcuModeSettingModeBean> getMode() {
		return mode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeSettingInsertRequest [modeName=");
		builder.append(modeName);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", mode=");
		builder.append(mode);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeSettingInsertRequest(String modeName, int groupId, List<RcuModeSettingModeBean> mode) {
		super();
		this.modeName = modeName;
		this.groupId = groupId;
		this.mode = mode;
	}

}
