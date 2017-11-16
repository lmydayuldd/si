package com.derex.cm.stb.api.request;

import java.io.Serializable;

public class StbRcuCommandRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -483768338180933898L;
	private String stbip;
	private String command;

	public StbRcuCommandRequest(String stbip, String command) {
		super();
		this.stbip = stbip;
		this.command = command;
	}

	public String getStbip() {
		return stbip;
	}

	public String getCommand() {
		return command;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbRcuCommandRequest [stbip=");
		builder.append(stbip);
		builder.append(", command=");
		builder.append(command);
		builder.append("]");
		return builder.toString();
	}

}
