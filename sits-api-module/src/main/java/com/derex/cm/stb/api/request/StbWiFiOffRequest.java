package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

public class StbWiFiOffRequest implements Serializable {

    private static final long serialVersionUID = 8317589456861857457L;
    private List<String> stbip;

    public StbWiFiOffRequest(List<String> stbip) {
	super();
	this.stbip = stbip;
    }

    public List<String> getStbip() {
	return stbip;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StbWiFiOffRequest [stbip=\n");
	builder.append(stbip);
	builder.append("]");
	return builder.toString();
    }

}
