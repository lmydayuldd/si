package com.sidc.rcu.hmi.modesetting.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModeUpdateRequest implements Serializable {
	private static final long serialVersionUID = -9009369494945670347L;
	private int modeid;
	private String modename;
	private List<Integer> devices = new ArrayList<Integer>();

	public int getModeid() {
		return modeid;
	}

	public String getModename() {
		return modename;
	}

	public List<Integer> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeUpdateRequest [modeid=");
		builder.append(modeid);
		builder.append(", modename=");
		builder.append(modename);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	public ModeUpdateRequest(int modeid, String modename, List<Integer> devices) {
		super();
		this.modeid = modeid;
		this.modename = modename;
		this.devices = devices;
	}

}
