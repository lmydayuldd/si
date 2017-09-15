package com.derex.cm.stb.api.response;

import java.io.Serializable;

public class ChannelListFileResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668252150726240502L;
	private String filename;
	public ChannelListFileResponse(String filename) {
		// TODO Auto-generated constructor stub
		this.filename = filename;
	}
	public String getFilename() {
		return filename;
	}
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelListFileResponse [filename=\n");
		builder.append(filename);
		builder.append("]");
		return builder.toString();
	}
}
