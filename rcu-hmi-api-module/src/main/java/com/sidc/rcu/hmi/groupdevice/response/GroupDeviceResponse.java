package com.sidc.rcu.hmi.groupdevice.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.rcu.hmi.groupdevice.bean.GroupDeviceBean;

public class GroupDeviceResponse implements Serializable {
	private static final long serialVersionUID = 4089862886692982153L;
	private String groupname;
	private List<RcuDeviceEnity> alldevices = new ArrayList<RcuDeviceEnity>();
	private List<GroupDeviceBean> groupdevices = new ArrayList<GroupDeviceBean>();

	public List<RcuDeviceEnity> getAlldevices() {
		return alldevices;
	}

	public List<GroupDeviceBean> getGroupdevices() {
		return groupdevices;
	}

	public String getGroupname() {
		return groupname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupDeviceResponse [groupname=");
		builder.append(groupname);
		builder.append(", alldevices=");
		builder.append(alldevices);
		builder.append(", groupdevices=");
		builder.append(groupdevices);
		builder.append("]");
		return builder.toString();
	}

	public GroupDeviceResponse(String groupname, List<RcuDeviceEnity> alldevices, List<GroupDeviceBean> groupdevices) {
		super();
		this.groupname = groupname;
		this.alldevices = alldevices;
		this.groupdevices = groupdevices;
	}

}
