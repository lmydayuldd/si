package com.sidc.blackcore.api.mobile.message.response;

import java.io.Serializable;

public class GuestMessageResponse implements Serializable {
	private static final long serialVersionUID = -6949249932759514339L;
	private String messageid;

	public String getMessageid() {
		return messageid;
	}

	public GuestMessageResponse(String messageid) {
		super();
		this.messageid = messageid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuestMessageResponse [messageid=");
		builder.append(messageid);
		builder.append("]");
		return builder.toString();
	}

}
