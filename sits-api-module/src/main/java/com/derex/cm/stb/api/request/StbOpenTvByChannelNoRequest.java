package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

public class StbOpenTvByChannelNoRequest implements Serializable {

	private static final long serialVersionUID = -3200638738032164467L;
	private List<String> stbip;
	private String channelno;

	public StbOpenTvByChannelNoRequest(List<String> stbip, String channelno) {
		super();
		this.stbip = stbip;
		this.channelno = channelno;
	}

	public List<String> getStbip() {
		return stbip;
	}

	public String getChannelno() {
		return channelno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbOpenTvByChannelNoRequest [stbip=\n");
		builder.append(stbip);
		builder.append(", channelno=\n");
		builder.append(channelno);
		builder.append("]");
		return builder.toString();
	}

}
