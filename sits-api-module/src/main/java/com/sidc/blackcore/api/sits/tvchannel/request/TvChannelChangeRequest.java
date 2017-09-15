package com.sidc.blackcore.api.sits.tvchannel.request;

import java.io.Serializable;

public class TvChannelChangeRequest implements Serializable {
	private static final long serialVersionUID = -7872811555805771941L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String channelno;
	private String ip;

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getChannelno() {
		return channelno;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TvChannelChangeRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", channelno=");
		builder.append(channelno);
		builder.append(", ip=");
		builder.append(ip);
		builder.append("]");
		return builder.toString();
	}

	public TvChannelChangeRequest(String publickey, String privatekey, String roomno, String channelno, String ip) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.channelno = channelno;
		this.ip = ip;
	}

}
