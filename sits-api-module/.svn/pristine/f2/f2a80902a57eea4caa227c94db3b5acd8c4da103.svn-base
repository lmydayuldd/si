package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;

public class ChatMettingHistoricalMessageBean implements Serializable {
	private static final long serialVersionUID = -671012489214919841L;
	private String staffid;
	private String messageid;
	private int type;
	private String message;
	private String creationdate;

	public String getStaffid() {
		return staffid;
	}

	public String getMessageid() {
		return messageid;
	}

	public int getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatMettingHistoricalMessageBean [staffid=");
		builder.append(staffid);
		builder.append(", messageid=");
		builder.append(messageid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", message=");
		builder.append(message);
		builder.append(", creationdate=");
		builder.append(creationdate);
		builder.append("]");
		return builder.toString();
	}

	public ChatMettingHistoricalMessageBean(String staffid, String messageid, int type, String message,
			String creationdate) {
		super();
		this.staffid = staffid;
		this.messageid = messageid;
		this.type = type;
		this.message = message;
		this.creationdate = creationdate;
	}

	public ChatMettingHistoricalMessageBean(String messageid, String message, String creationdate) {
		super();
		this.messageid = messageid;
		this.message = message;
		this.creationdate = creationdate;
	}

}
