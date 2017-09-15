package com.sidc.blackcore.api.sits.movie.bean;

import java.io.Serializable;

public class VideoServerInfoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2863400655957911030L;
	private String type;
	private String host;
	private String port;
	private String volume;

	public String getType() {
		return type;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getVolume() {
		return volume;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VideoServerInfoBean [type=");
		builder.append(type);
		builder.append(", host=");
		builder.append(host);
		builder.append(", port=");
		builder.append(port);
		builder.append(", volume=");
		builder.append(volume);
		builder.append("]");
		return builder.toString();
	}

	public VideoServerInfoBean(String type, String host, String port, String volume) {
		super();
		this.type = type;
		this.host = host;
		this.port = port;
		this.volume = volume;
	}

}
