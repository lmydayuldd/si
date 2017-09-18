package com.sidc.rcu.hmi.response;

import java.util.Date;

public class Apidocument implements java.io.Serializable {
	private static final long serialVersionUID = -2259200538938396562L;
	private int status;
	private String message;
	private Object data;
	private long time;

	public Apidocument(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.time = new Date().getTime();
	}

	public Apidocument(int status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.time = new Date().getTime();
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public long getTime() {
		return time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIDocument [status=\n");
		builder.append(status);
		builder.append(", message=\n");
		builder.append(message);
		builder.append(", data=\n");
		builder.append(data);
		builder.append(", time=\n");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}
}
