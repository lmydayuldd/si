package com.sidc.blackcore.api.mobile.message.request;

import java.io.Serializable;

import com.sidc.blackcore.api.mobile.message.bean.MessageInfoBean;

public class HotelMessageRequest implements Serializable {
	private static final long serialVersionUID = 5080313642930968453L;
	private String token;
	private String staffid;
	private String type;
	private MessageInfoBean data;

	public String getToken() {
		return token;
	}

	public String getStaffid() {
		return staffid;
	}

	public String getType() {
		return type;
	}

	public MessageInfoBean getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HotelMessageRequest [token=");
		builder.append(token);
		builder.append(", staffid=");
		builder.append(staffid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

	public HotelMessageRequest(String token, String staffid, String type, MessageInfoBean data) {
		super();
		this.token = token;
		this.staffid = staffid;
		this.type = type;
		this.data = data;
	}

}
