/**
 * 
 */
package com.sidc.raudp.bean;

import java.util.List;

/**
 * @author Nandin
 *
 */
public class BlubBean implements java.io.Serializable {

	private static final long serialVersionUID = 3912626311165897031L;
	private int command;
	private List<BlubDeviceBean> devices;

	public BlubBean(int command, List<BlubDeviceBean> devices) {
		super();
		this.command = command;
		this.devices = devices;
	}

	public int getCommand() {
		return command;
	}

	public List<BlubDeviceBean> getDevices() {
		return devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlubBean [command=");
		builder.append(command);
		builder.append(", \n\tdevices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

}
