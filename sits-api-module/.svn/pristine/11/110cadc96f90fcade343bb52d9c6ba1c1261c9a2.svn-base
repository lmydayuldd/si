/**
 * 
 */
package com.sidc.raudp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nandin
 *
 */
public class RCUModule implements Serializable {

	private static final long serialVersionUID = 7453483573803668612L;
	private String catalogue;
	private int command;
	private List<Device> devices;

	public RCUModule() {
		super();
	}

	public RCUModule(String catalogue, int command, List<Device> devices) {
		super();
		this.catalogue = catalogue;
		this.command = command;
		this.devices = devices;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCUModule [\n\tcatalogue=");
		builder.append(catalogue);
		builder.append(", command=");
		builder.append(command);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

}
