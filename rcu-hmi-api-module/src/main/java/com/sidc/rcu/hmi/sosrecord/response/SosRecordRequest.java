package com.sidc.rcu.hmi.sosrecord.response;

import java.io.Serializable;

public class SosRecordRequest implements Serializable {
	private static final long serialVersionUID = -4018560849747685961L;
	private String roomno;
	private String action;
	private String clientrole;
	private String clientip;

	public String getRoomno() {
		return roomno;
	}

	public String getAction() {
		return action;
	}

	public String getClientrole() {
		return clientrole;
	}

	public String getClientip() {
		return clientip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SosRecordRequest [roomno=");
		builder.append(roomno);
		builder.append(", action=");
		builder.append(action);
		builder.append(", clientrole=");
		builder.append(clientrole);
		builder.append(", clientip=");
		builder.append(clientip);
		builder.append("]");
		return builder.toString();
	}

	public SosRecordRequest(String roomno, String action, String clientrole, String clientip) {
		super();
		this.roomno = roomno;
		this.action = action;
		this.clientrole = clientrole;
		this.clientip = clientip;
	}

}
