package com.sidc.blackcore.api.sits.token.request;

import java.io.Serializable;

import com.sidc.blackcore.api.sits.token.bean.InfoBean;

public class MobilePrivateTokenInsertRequest implements Serializable {
	private static final long serialVersionUID = 4250244804267142677L;

	private String publickey;
	private String roomno;
	private String ip;
	private int type;
	private InfoBean info;

	public String getPublickey() {
		return publickey;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getIp() {
		return ip;
	}

	public int getType() {
		return type;
	}

	public InfoBean getInfo() {
		return info;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MobilePrivateTokenInsertRequest [publickey=");
		builder.append(publickey);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", type=");
		builder.append(type);
		builder.append(", info=");
		builder.append(info);
		builder.append("]");
		return builder.toString();
	}

	public MobilePrivateTokenInsertRequest(String publickey, String roomno, String ip, int type, InfoBean info) {
		super();
		this.publickey = publickey;
		this.roomno = roomno;
		this.ip = ip;
		this.type = type;
		this.info = info;
	}

}
