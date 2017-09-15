/**
 * 
 */
package com.sidc.blackcore.api.ra.rcugroup.request;

import java.util.ArrayList;
import java.util.List;

public class RcuGroupInsertRequest implements java.io.Serializable {

	private static final long serialVersionUID = 2713030516975816336L;
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
		builder.append("RcuGroupInsertRequest [groupname=");
		builder.append(groupname);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public RcuGroupInsertRequest(String groupname, List<Integer> devices) {
		super();
		this.groupname = groupname;
		this.devices = devices;
	}

}
