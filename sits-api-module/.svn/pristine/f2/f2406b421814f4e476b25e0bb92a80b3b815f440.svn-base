package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;

public class FcmResultsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8957561785871467639L;
	private String message_id;
	private String registration_id;
	private String error;

	public String getMessage_id() {
		return message_id;
	}

	public String getRegistration_id() {
		return registration_id;
	}

	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FcmResultsBean [message_id=");
		builder.append(message_id);
		builder.append(", registration_id=");
		builder.append(registration_id);
		builder.append(", error=");
		builder.append(error);
		builder.append("]");
		return builder.toString();
	}

	public FcmResultsBean(String message_id, String registration_id, String error) {
		super();
		this.message_id = message_id;
		this.registration_id = registration_id;
		this.error = error;
	}

}
