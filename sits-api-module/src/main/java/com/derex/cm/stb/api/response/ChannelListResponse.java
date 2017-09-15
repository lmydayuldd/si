package com.derex.cm.stb.api.response;

import java.io.Serializable;
import java.util.List;

public class ChannelListResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7169377401858464402L;
	private List<ChannelListFileResponse> list;
	public ChannelListResponse(List<ChannelListFileResponse> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
	}
	public List<ChannelListFileResponse> getList() {
		return list;
	}
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelListResponse [list=\n");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
}
