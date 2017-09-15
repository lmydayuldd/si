package com.sidc.dao.rcu.command.response;

import java.io.Serializable;

/**
 * 
 * @author Allen Huang
 *
 */
public class RcuModelDevice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3745215659089575325L;
	private String keycode;
	private RcuModelCondition condition;
	
	public RcuModelDevice(String keycode, RcuModelCondition condition) {
		// TODO Auto-generated constructor stub
		this.keycode = keycode;
		this.condition = condition;
	}

	public String getKeycode() {
		return keycode;
	}

	public RcuModelCondition getCondition() {
		return condition;
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModelDevice [keycode=\n");
		builder.append(keycode);
		builder.append(", condition=\n");
		builder.append(condition);
		builder.append("]");
		return builder.toString();
	}
}
