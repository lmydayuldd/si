package com.sidc.blackcore.api.mobile.message.request;

import java.io.Serializable;

import com.sidc.blackcore.api.mobile.message.bean.MessageInfoBean;

public class NewslettersRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4575499740115839365L;
	private String token;
	private String staffid;
	private String type;
	private MessageInfoBean data;

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public MessageInfoBean getData() {
		return data;
	}

	public String getStaffid() {
		return staffid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewslettersRequest [token=");
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

	public NewslettersRequest(String token, String staffid, String type, MessageInfoBean data) {
		super();
		this.token = token;
		this.staffid = staffid;
		this.type = type;
		this.data = data;
	}

}
