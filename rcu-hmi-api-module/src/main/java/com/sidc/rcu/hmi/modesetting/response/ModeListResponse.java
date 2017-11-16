package com.sidc.rcu.hmi.modesetting.response;

import java.io.Serializable;

public class ModeListResponse implements Serializable {
	private static final long serialVersionUID = -3783642489462739265L;
	private int id;
	private String keyname;
	private String content;
	private int timer;
	private int status;

	public int getId() {
		return id;
	}

	public String getKeyname() {
		return keyname;
	}

	public String getContent() {
		return content;
	}

	public int getTimer() {
		return timer;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeListResponse [id=");
		builder.append(id);
		builder.append(", keyname=");
		builder.append(keyname);
		builder.append(", content=");
		builder.append(content);
		builder.append(", timer=");
		builder.append(timer);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public ModeListResponse(int id, String keyname, String content, int timer, int status) {
		super();
		this.id = id;
		this.keyname = keyname;
		this.content = content;
		this.timer = timer;
		this.status = status;
	}

}
