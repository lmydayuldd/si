package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

import com.derex.cm.stb.api.display.BootMode;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbBootModeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5143530750844426756L;
	private List<String> stbip;
	private BootMode param;
	private String value;
	public StbBootModeRequest(List<String> stbip, BootMode param, String value) {
		this.stbip = stbip;
		this.param = param;
		this.value = value;
	}
	public List<String> getStbip() {
		return stbip;
	}
	public String getParam() {
		return param.toString();
	}
	public String getValue() {
		return value;
	}
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("StbBootModeRequest [stbip=\n");
		builder.append(stbip);
		builder.append(", param=\n");
		builder.append(param.toString());
		builder.append(", value=\n");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
