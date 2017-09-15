package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

public class StbCheckoutRequest implements Serializable {

    private static final long serialVersionUID = -5607373942132971554L;
    private List<String> stbip;

    public StbCheckoutRequest(List<String> stbip) {
	super();
	this.stbip = stbip;
    }

    public List<String> getStbip() {
	return stbip;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StbCheckoutRequest [stbip=\n");
	builder.append(stbip);
	builder.append("]");
	return builder.toString();
    }

}
