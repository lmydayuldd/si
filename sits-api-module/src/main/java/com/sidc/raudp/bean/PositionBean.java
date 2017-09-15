/**
 * 
 */
package com.sidc.raudp.bean;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class PositionBean implements Serializable {

	private static final long serialVersionUID = -1735271108898428380L;
	private int address = -1;
	private int circuit;

	public PositionBean(int address, int circuit) {
		super();
		this.address = address;
		this.circuit = circuit;
	}

	public int getAddress() {
		return address;
	}

	public int getCircuit() {
		return circuit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PositionBean [\n\taddress=");
		builder.append(address);
		builder.append(", circuit=");
		builder.append(circuit);
		builder.append("]");
		return builder.toString();
	}

}
