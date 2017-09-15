/**
 * 
 */
package com.sidc.raudp.bean;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class BlubDeviceBean implements Serializable {

	private static final long serialVersionUID = -1735271108898428380L;
	private String name;
	private PositionBean position;

	public BlubDeviceBean(String name, PositionBean position) {
		super();
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public PositionBean getPosition() {
		return position;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlubDeviceBean [\n\tname=");
		builder.append(name);
		builder.append(", position=");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}

}
