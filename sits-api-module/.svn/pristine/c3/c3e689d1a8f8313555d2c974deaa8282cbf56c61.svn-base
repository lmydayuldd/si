/**
 * 
 */
package com.derex.cm.stb.api.request;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class StbStautsRequest implements Serializable {

	private static final long serialVersionUID = -4372941368365540039L;
	private int floor = 0;
	private int order = 0;
	private int stbStatus = 0;

	public StbStautsRequest(int floor, int order, int stbStatus) {
		super();
		this.floor = floor;
		this.order = order;
		this.stbStatus = stbStatus;
	}

	public int getFloor() {
		return floor;
	}

	public int getOrder() {
		return order;
	}

	public int getStbStatus() {
		return stbStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbStautsRequest [floor=\n");
		builder.append(floor);
		builder.append(", order=\n");
		builder.append(order);
		builder.append(", stbStatus=\n");
		builder.append(stbStatus);
		builder.append("]");
		return builder.toString();
	}

}
