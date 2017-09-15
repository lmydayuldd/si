package com.sidc.blackcore.api.sits.token.request;

import java.io.Serializable;

import com.sidc.blackcore.api.sits.token.bean.InfoBean;

public class ChatTokenRequest implements Serializable {

	private static final long serialVersionUID = 7283014539745925745L;
	private String userid;
	private InfoBean info;

	public String getUserid() {
		return userid;
	}

	public InfoBean getInfo() {
		return info;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatTokenRequest [userid=");
		builder.append(userid);
		builder.append(", info=");
		builder.append(info);
		builder.append("]");
		return builder.toString();
	}

	public ChatTokenRequest(String userid, InfoBean info) {
		super();
		this.userid = userid;
		this.info = info;
	}

}
