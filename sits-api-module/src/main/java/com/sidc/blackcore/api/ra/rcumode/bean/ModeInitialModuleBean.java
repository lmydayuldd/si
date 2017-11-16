/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;
import java.util.List;

public class ModeInitialModuleBean implements Serializable {
	private static final long serialVersionUID = 6376376307042661033L;
	private int groupid;
	private String content;
	private List<Integer> deviceid;

	public int getGroupid() {
		return groupid;
	}

	public String getContent() {
		return content;
	}

	public List<Integer> getDeviceid() {
		return deviceid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialModuleBean [groupid=");
		builder.append(groupid);
		builder.append(", content=");
		builder.append(content);
		builder.append(", deviceid=");
		builder.append(deviceid);
		builder.append("]");
		return builder.toString();
	}

	public ModeInitialModuleBean(int groupid, String content, List<Integer> deviceid) {
		super();
		this.groupid = groupid;
		this.content = content;
		this.deviceid = deviceid;
	}

}
