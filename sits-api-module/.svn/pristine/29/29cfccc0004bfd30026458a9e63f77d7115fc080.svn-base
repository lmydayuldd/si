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
public class AirconditionBean implements Serializable {

	private static final long serialVersionUID = 6867391875932011931L;

	private int command;
	private List<Integer> machines;


	public AirconditionBean(int command, List<Integer> machines) {
		super();
		this.command = command;
		this.machines = machines;
	}

	public int getCommand() {
		return command;
	}

	public List<Integer> getMachines() {
		return machines;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AirconditionBean [\n\tcommand=");
		builder.append(command);
		builder.append(", machines=");
		builder.append(machines);
		builder.append("]");
		return builder.toString();
	}

}
