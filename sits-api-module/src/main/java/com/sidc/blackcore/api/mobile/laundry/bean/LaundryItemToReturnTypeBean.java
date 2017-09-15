package com.sidc.blackcore.api.mobile.laundry.bean;

import java.io.Serializable;

public class LaundryItemToReturnTypeBean implements Serializable {
	private static final long serialVersionUID = 4130668304964491286L;
	private int returntypeid;

	public int getReturntypeid() {
		return returntypeid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryItemToReturnTypeBean [returntypeid=");
		builder.append(returntypeid);
		builder.append("]");
		return builder.toString();
	}

	public LaundryItemToReturnTypeBean(int returntypeid) {
		super();
		this.returntypeid = returntypeid;
	}

}
