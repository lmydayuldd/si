/**
 * 
 */
package com.sidc.rcu.connector.bean.command;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class KeyCodeInfo {

	private String roomType;
	private String catalogue;
	private String keycode;
	private int commander;
	private Serializable appender;

	public KeyCodeInfo(String roomType, String catalogue, String keycode) {
		super();
		this.roomType = roomType;
		this.catalogue = catalogue;
		this.keycode = keycode;
	}

	public KeyCodeInfo(String roomType, String catalogue, String keycode, int commander, Serializable appender) {
		super();
		this.roomType = roomType;
		this.catalogue = catalogue;
		this.keycode = keycode;
		this.commander = commander;
		this.appender = appender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keycode == null) ? 0 : keycode.hashCode());
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyCodeInfo other = (KeyCodeInfo) obj;
		if (keycode == null) {
			if (other.keycode != null)
				return false;
		} else if (!keycode.equals(other.keycode))
			return false;
		if (roomType == null) {
			if (other.roomType != null)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		return true;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public String getKeycode() {
		return keycode;
	}

	public int getCommander() {
		return commander;
	}

	public Serializable getAppender() {
		return appender;
	}

	public String getRoomType() {
		return roomType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyCodeInfo [\n\tcatalogue=");
		builder.append(catalogue);
		builder.append(", keycode=");
		builder.append(keycode);
		builder.append(", commander=");
		builder.append(commander);
		builder.append(", appender=");
		builder.append(appender);
		builder.append("]");
		return builder.toString();
	}

}
