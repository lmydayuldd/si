package com.sidc.quartz.api.request;

import java.io.Serializable;
import java.util.List;

import com.sidc.quartz.api.response.ScheduleStateResponse;

public class ScheduleListRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7594321817517331460L;
	private List<ScheduleStateResponse> list;
	
	public ScheduleListRequest(List<ScheduleStateResponse> list) {
		super();
		this.list = list;
	}

	public List<ScheduleStateResponse> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleListRequest [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

}
