/**
 * 
 */
package com.derex.cm.stb.api.response;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class StbStatusConnectionResponse implements Serializable {
    private static final long serialVersionUID = 8940218037755768950L;
    private String ip;
    private int status;
    private String middleware;

    public StbStatusConnectionResponse(String ip, int status, String middleware) {
	super();
	this.ip = ip;
	this.status = status;
	this.middleware = middleware;
    }

    public String getIp() {
	return ip;
    }

    public int getStatus() {
	return status;
    }

    public String getMiddleware() {
	return middleware;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StbStatusConnectionInfo [ip=\n");
	builder.append(ip);
	builder.append(", status=\n");
	builder.append(status);
	builder.append(", middleware=\n");
	builder.append(middleware);
	builder.append("]");
	return builder.toString();
    }

}
