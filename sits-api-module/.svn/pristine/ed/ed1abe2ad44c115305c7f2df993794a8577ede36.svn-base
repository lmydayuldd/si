package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

import com.derex.cm.stb.api.display.AttributeType;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbPropertySettingRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6822155169496393806L;
	private List<String> stbip;
	private AttributeType key;
	private String value;
	public StbPropertySettingRequest(List<String> stbip, AttributeType key, String value) {
		this.stbip = stbip;
		this.key = key;
		this.value = value;
	}
	public List<String> getStbip() {
		return stbip;
	}
	public String getKey() {
		return key.toString();
	}
	public String getValue() {
		return value;
	}
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("StbSingleIniTemplateRequest [stbip=\n");
		builder.append(stbip);
		builder.append(", name=\n");
		builder.append(key.toString());
		builder.append(", value=\n");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
