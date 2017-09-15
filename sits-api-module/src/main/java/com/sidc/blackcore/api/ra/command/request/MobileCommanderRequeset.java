package com.sidc.blackcore.api.ra.command.request;

import java.io.Serializable;

public class MobileCommanderRequeset implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -349138591445096565L;
	private String publickey;
	private String privatekey;
	private String uuid;
	private String ip;
	private String roomno;
	private String modename;
	private String keycode;
	private Object data;

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getUuid() {
		return uuid;
	}

	public String getIp() {
		return ip;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getKeycode() {
		return keycode;
	}

	public Object getData() {
		return data;
	}

	public String getModename() {
		return modename;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MobileCommanderRequeset [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", modename=");
		builder.append(modename);
		builder.append(", keycode=");
		builder.append(keycode);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

	public MobileCommanderRequeset(String publickey, String privatekey, String uuid, String ip, String roomno,
			String modename, String keycode, Object data) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.uuid = uuid;
		this.ip = ip;
		this.roomno = roomno;
		this.modename = modename;
		this.keycode = keycode;
		this.data = data;
	}

}
