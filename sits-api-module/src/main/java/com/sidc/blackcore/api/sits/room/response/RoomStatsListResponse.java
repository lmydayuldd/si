/**
 * 
 */
package com.sidc.blackcore.api.sits.room.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomStatsListResponse implements Serializable {
	private static final long serialVersionUID = 1271205489548484730L;
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
		builder.append("RoomStatsListResponse [checkinrooms=");
		builder.append(checkinrooms);
		builder.append(", checkoutrooms=");
		builder.append(checkoutrooms);
		builder.append("]");
		return builder.toString();
	}

	public RoomStatsListResponse(List<String> checkinrooms, List<String> checkoutrooms) {
		super();
		this.checkinrooms = checkinrooms;
		this.checkoutrooms = checkoutrooms;
	}

}
