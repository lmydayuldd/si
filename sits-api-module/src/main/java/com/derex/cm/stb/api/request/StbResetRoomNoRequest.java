package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

public class StbResetRoomNoRequest implements Serializable {

	private static final long serialVersionUID = 585236294561524271L;
	private List<String> stbip;

	public StbResetRoomNoRequest(List<String> stbip) {
		super();
		this.stbip = stbip;
	}

	public List<String> getStbip() {
		return stbip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbOpenTvByChannelNoRequest [stbip=\n");
		builder.append(stbip);
		builder.append("]");
		return builder.toString();
	}

}
