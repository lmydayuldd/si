package com.sidc.rcu.hmi.sosrecord.bean;

import java.io.Serializable;
import java.util.Date;

public class SosRecordBean implements Serializable {
	private static final long serialVersionUID = -6134643622672776354L;
	private String roomno;
	private String action;
	private String clientip;
	private Date expiredtime;
	private Date creationtime;

	public String getRoomno() {
		return roomno;
	}

	public String getAction() {
		return action;
	}

	public String getClientip() {
		return clientip;
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public Date getExpiredtime() {
		return expiredtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SosRecordBean [roomno=");
		builder.append(roomno);
		builder.append(", action=");
		builder.append(action);
		builder.append(", clientip=");
		builder.append(clientip);
		builder.append(", expiredtime=");
		builder.append(expiredtime);
		builder.append(", creationtime=");
		builder.append(creationtime);
		builder.append("]");
		return builder.toString();
	}

	public SosRecordBean(String roomno, String action, String clientip, Date creationtime) {
		super();
		this.roomno = roomno;
		this.action = action;
		this.clientip = clientip;
		this.creationtime = creationtime;
	}

	public SosRecordBean(String roomno, String action, String clientip, Date expiredtime, Date creationtime) {
		super();
		this.roomno = roomno;
		this.action = action;
		this.clientip = clientip;
		this.expiredtime = expiredtime;
		this.creationtime = creationtime;
	}

}
