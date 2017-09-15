/**
 * 
 */
package com.sidc.raudp.bean;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1053088307054261860L;
	private String keycode;
	private PositionBean position;

	public Device() {
		super();
	}

	public Device(String keycode, PositionBean position) {
		super();
		this.keycode = keycode;
		this.position = position;
	}

	public String getKeycode() {
		return keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public PositionBean getPosition() {
		return position;
	}

	public void setPosition(PositionBean position) {
		this.position = position;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Devices [\n\tkeycode=");
		builder.append(keycode);
		builder.append(", position=");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}

}
