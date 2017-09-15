package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

public class StbWakeupRequest implements Serializable {

    private static final long serialVersionUID = 8317589456861857457L;
    private List<String> stbip;
    private String channelno;

    public StbWakeupRequest(List<String> stbip, String channelno) {
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
	builder.append("StbWakeupRequest [stbip=\n");
	builder.append(stbip);
	builder.append(", channelno=\n");
	builder.append(channelno);
	builder.append("]");
	return builder.toString();
    }

}
