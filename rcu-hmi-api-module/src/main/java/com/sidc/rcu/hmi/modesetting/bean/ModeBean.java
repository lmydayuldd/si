package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;

public class ModeBean implements Serializable {

	private static final long serialVersionUID = 3345442948401044673L;
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

	public int getTimer() {
		return timer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeBean [id=");
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

	public ModeBean(int id, String keyname, String content, int timer) {
		super();
		this.id = id;
		this.keyname = keyname;
		this.content = content;
		this.timer = timer;
	}

}
