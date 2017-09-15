/**
 * 
 */
package com.sidc.blackcore.api.sits.common.response;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class CommonResponse implements Serializable {
	private static final long serialVersionUID = -6497596379106859607L;
	private int status;
	private String message;
	private String data;
	private String time;

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getData() {
		return data;
	}

	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenResponse [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", data=");
		builder.append(data);
		builder.append(", time=");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}

	public CommonResponse(int status, String message, String data, String time) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.time = time;
	}

}
