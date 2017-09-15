package com.sidc.blackcore.api.ra.rcumodesetting.response;

import java.io.Serializable;

public class RcuDefaultModeResponse implements Serializable {

	private static final long serialVersionUID = -4032853856285932128L;
	private int id;
	private String keyname;
	private String content;
	private int timer;

	public int getId() {
		return id;
	}

	public String getKeyname() {
		return keyname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTimer() {
		return timer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDefaultModeResponse [id=");
		builder.append(id);
		builder.append(", keyname=");
		builder.append(keyname);
		builder.append(", content=");
		builder.append(content);
		builder.append(", timer=");
		builder.append(timer);
		builder.append("]");
		return builder.toString();
	}

	public RcuDefaultModeResponse(int id, String keyname, String content, int timer) {
		super();
		this.id = id;
		this.keyname = keyname;
		this.content = content;
		this.timer = timer;
	}

}
