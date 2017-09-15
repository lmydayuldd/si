package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

public class StbRedirectPageRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5607373942132971554L;
    private List<String> stbip;
    private String url;

    public StbRedirectPageRequest(List<String> stbip, String url) {
	// TODO Auto-generated constructor stub
	this.stbip = stbip;
	this.url = url;
    }

    public List<String> getStbip() {
	return stbip;
    }

    public String getUrl() {
	return url;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StbRedirectPageRequest [stbip=\n");
	builder.append(stbip);
	builder.append(", url=\n");
	builder.append(url);
	builder.append("]");
	return builder.toString();
    }

}
