/**
 * 
 */
package com.sidc.rcu.connector.bean.command;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class RCUCommander implements Serializable {

	private static final long serialVersionUID = -1480283770622318362L;

	private String uuid;
	private String roomno;
	private String keycode;
	private Object data;

	public RCUCommander(String uuid, String roomno, String keycode) {
		super();
		this.uuid = uuid;
		this.roomno = roomno;
		this.keycode = keycode;
	}

	public RCUCommander(String uuid, String roomno, String keycode, Serializable data) {
		super();
		this.uuid = uuid;
		this.roomno = roomno;
		this.keycode = keycode;
		this.data = data;
	}

	public String getUuid() {
		return uuid;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCUCommander [\n\troomno=");
		builder.append(roomno);
		builder.append(", keycode=");
		builder.append(keycode);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
