package com.derex.cm.stb.api.request;

import java.io.Serializable;

public class StbRemoteControlRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8110594574197357835L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String stbip;
	private String function;
	private String action;

	public StbRemoteControlRequest(String publickey, String privatekey, String roomno, String stbip, String function,
			String action) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.stbip = stbip;
		this.function = function;
		this.action = action;
	}

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getStbip() {
		return stbip;
	}

	public String getFunction() {
		return function;
	}

	public String getAction() {
		return action;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbRemoteControlRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", stbip=");
		builder.append(stbip);
		builder.append(", function=");
		builder.append(function);
		builder.append(", action=");
		builder.append(action);
		builder.append("]");
		return builder.toString();
	}
}
