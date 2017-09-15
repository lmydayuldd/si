package com.sidc.blackcore.api.sits.room.bean;

import java.io.Serializable;

public class PayServiceBean implements Serializable {
	private static final long serialVersionUID = 2601199868213015488L;
	private boolean ischarge;
	private boolean ispayservice;
	private boolean isadult;

	public boolean isCharge() {
		return ischarge;
	}

	public void setisCharge(boolean ischarge) {
		this.ischarge = ischarge;
	}

	public boolean isPayservice() {
		return ispayservice;
	}

	public boolean isAdult() {
		return isadult;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayServiceBean [ischarge=");
		builder.append(ischarge);
		builder.append(", ispayservice=");
		builder.append(ispayservice);
		builder.append(", isadult=");
		builder.append(isadult);
		builder.append("]");
		return builder.toString();
	}

	public PayServiceBean(boolean ispayservice, boolean isadult) {
		super();
		this.ispayservice = ispayservice;
		this.isadult = isadult;
	}

}
