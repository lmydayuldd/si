/**
 * 
 */
package com.sidc.blackcore.api.sits.userauthorization.response;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class UserAuthorizationResponse implements Serializable {
	private static final long serialVersionUID = -5594737920606210610L;
	private String functionname;
	private String functioncode;
	private boolean read;
	private boolean write;

	public String getFunctionname() {
		return functionname;
	}

	public boolean isRead() {
		return read;
	}

	public boolean isWrite() {
		return write;
	}

	public String getFunctioncode() {
		return functioncode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAuthorizationResponse [functionname=");
		builder.append(functionname);
		builder.append(", functioncode=");
		builder.append(functioncode);
		builder.append(", read=");
		builder.append(read);
		builder.append(", write=");
		builder.append(write);
		builder.append("]");
		return builder.toString();
	}

	public UserAuthorizationResponse(String functionname, String functioncode, boolean read, boolean write) {
		super();
		this.functionname = functionname;
		this.functioncode = functioncode;
		this.read = read;
		this.write = write;
	}

}
