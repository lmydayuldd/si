package com.sidc.quartz.api.request;

import java.io.Serializable;
import java.util.List;

import com.sidc.quartz.api.response.CheckOutRoomScheduleResposne;


public class CheckOutRoomScheduleListRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8877247747100160895L;
	private List<CheckOutRoomScheduleResposne> list;
	
	public CheckOutRoomScheduleListRequest(List<CheckOutRoomScheduleResposne> list) {
		super();
		this.list = list;
	}

	public List<CheckOutRoomScheduleResposne> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CheckOutRoomScheduleListRequest [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
	
	
}
