package com.sidc.blackcore.api.sits.roomservice.reponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceCategoryBean;

public class RoomServiceResponse implements Serializable {
	private static final long serialVersionUID = 80839414810882462L;
	private String currency;
	private List<RoomServiceCategoryBean> list = new ArrayList<RoomServiceCategoryBean>();

	public String getCurrency() {
		return currency;
	}

	public List<RoomServiceCategoryBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceResponse [currency=");
		builder.append(currency);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceResponse(String currency, List<RoomServiceCategoryBean> list) {
		super();
		this.currency = currency;
		this.list = list;
	}

}
