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
public class ServiceBean implements Serializable {

	private static final long serialVersionUID = -5076965269613531882L;

	private int command;
	private List<String> behavior;

	public ServiceBean(int command, List<String> behavior) {
		super();
		this.command = command;
		this.behavior = behavior;
	}

	public int getCommand() {
		return command;
	}

	public List<String> getBehavior() {
		return behavior;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceBean [command=");
		builder.append(command);
		builder.append(", \n\tbehavior=");
		builder.append(behavior);
		builder.append("]");
		return builder.toString();
	}

}
