package com.sidc.blackcore.api.sits.token.request;

import java.io.Serializable;

import com.sidc.blackcore.api.sits.token.bean.InfoBean;

public class MobilePinCodeTokenRequest implements Serializable {
	private static final long serialVersionUID = -7058175190318907202L;
	private String pincode;
	private InfoBean info;

	public String getPincode() {
		return pincode;
	}

	public InfoBean getInfo() {
		return info;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MobilePinCodeTokenRequest [pincode=");
		builder.append(pincode);
		builder.append(", info=");
		builder.append(info);
		builder.append("]");
		return builder.toString();
	}

	public MobilePinCodeTokenRequest(String pincode, InfoBean info) {
		super();
		this.pincode = pincode;
		this.info = info;
	}
}
