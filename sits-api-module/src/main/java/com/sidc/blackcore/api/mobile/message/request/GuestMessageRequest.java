package com.sidc.blackcore.api.mobile.message.request;

import java.io.Serializable;

public class GuestMessageRequest implements Serializable {

	private static final long serialVersionUID = -4699211223958663852L;

	private String publickey;
	private String privatekey;
	private String roomno;
	private String guestname;
	private String message;

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getGuestname() {
		return guestname;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuestMessageRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", guestname=");
		builder.append(guestname);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

	public GuestMessageRequest(String publickey, String privatekey, String roomno, String guestname, String message) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.guestname = guestname;
		this.message = message;
	}

}
