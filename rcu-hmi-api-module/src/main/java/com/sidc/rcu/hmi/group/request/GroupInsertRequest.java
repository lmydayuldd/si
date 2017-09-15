package com.sidc.rcu.hmi.group.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupInsertRequest implements Serializable {
	private static final long serialVersionUID = -8694326989499509506L;
	private String groupname;
	private List<Integer> devices = new ArrayList<Integer>();

	public String getGroupname() {
		return groupname;
	}

	public List<Integer> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupInsertRequest [groupname=");
		builder.append(groupname);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public GroupInsertRequest(String groupname, List<Integer> devices) {
		super();
		this.groupname = groupname;
		this.devices = devices;
	}

}
