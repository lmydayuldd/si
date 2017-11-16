package com.sidc.rcu.hmi.websocket.bean;

import java.io.Serializable;

public class WebSocketSosRecordBean implements Serializable {

	private static final long serialVersionUID = 8119098045964189609L;
	private boolean isstopalarm;

	public boolean isStopalarm() {
		return isstopalarm;
	}

	public void setIsstopalarm(boolean isstopalarm) {
		this.isstopalarm = isstopalarm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebSocketSosRecordBean [isstopalarm=");
		builder.append(isstopalarm);
		builder.append("]");
		return builder.toString();
	}

	public WebSocketSosRecordBean(boolean isstopalarm) {
		super();
		this.isstopalarm = isstopalarm;
	}

}
