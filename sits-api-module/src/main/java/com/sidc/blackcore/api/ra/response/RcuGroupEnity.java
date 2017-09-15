/**
 * 
 */
package com.sidc.blackcore.api.ra.response;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class RcuGroupEnity implements Serializable {

	private static final long serialVersionUID = -7985229991903432999L;
	private int id;
	private String name;

	public RcuGroupEnity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupEnity [\n\tid=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
