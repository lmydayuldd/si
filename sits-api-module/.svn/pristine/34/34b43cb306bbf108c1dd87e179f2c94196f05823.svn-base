package com.sidc.blackcore.api.sits.roomservice.reponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceInfoBean;

public class RoomServiceListResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5208682426112920179L;
	private String currency;
	private List<RoomServiceInfoBean> list = new ArrayList<RoomServiceInfoBean>();

	public String getCurrency() {
		return currency;
	}

	public List<RoomServiceInfoBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceListResponse [currency=");
		builder.append(currency);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceListResponse(String currency, List<RoomServiceInfoBean> list) {
		super();
		this.currency = currency;
		this.list = list;
	}

}
