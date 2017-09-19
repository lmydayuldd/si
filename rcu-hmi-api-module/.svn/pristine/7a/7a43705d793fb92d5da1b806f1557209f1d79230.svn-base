package com.sidc.rcu.hmi.groupdevice.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.groupdevice.bean.RcuGroupDeviceInsertBean;

public class RcuGroupDeviceInsertRequest implements Serializable {
	private static final long serialVersionUID = 9095969361443176814L;
	private List<RcuGroupDeviceInsertBean> groups = new ArrayList<RcuGroupDeviceInsertBean>();

	public List<RcuGroupDeviceInsertBean> getGroups() {
		return groups;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupDeviceInsertRequest [groups=");
		builder.append(groups);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupDeviceInsertRequest(List<RcuGroupDeviceInsertBean> groups) {
		super();
		this.groups = groups;
	}

}
