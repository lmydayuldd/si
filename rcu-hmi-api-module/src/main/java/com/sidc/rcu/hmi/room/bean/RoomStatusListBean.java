package com.sidc.rcu.hmi.room.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomStatusListBean implements Serializable {
	private static final long serialVersionUID = -6309056018372270538L;
	private List<String> checkinrooms = new ArrayList<String>();
	private List<String> checkoutrooms = new ArrayList<String>();

	public List<String> getCheckinrooms() {
		return checkinrooms;
	}

	public List<String> getCheckoutrooms() {
		return checkoutrooms;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomStatusListBean [checkinrooms=");
		builder.append(checkinrooms);
		builder.append(", checkoutrooms=");
		builder.append(checkoutrooms);
		builder.append("]");
		return builder.toString();
	}

	public RoomStatusListBean(List<String> checkinrooms, List<String> checkoutrooms) {
		super();
		this.checkinrooms = checkinrooms;
		this.checkoutrooms = checkoutrooms;
	}

}
