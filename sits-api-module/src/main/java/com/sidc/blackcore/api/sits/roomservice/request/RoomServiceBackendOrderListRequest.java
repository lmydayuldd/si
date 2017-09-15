package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;

public class RoomServiceBackendOrderListRequest implements Serializable {
	private static final long serialVersionUID = -4056582846769821919L;
	private String token;
	private String staffid;
	private String langcode;
	private String roomno;
	private int orderid;
	private String status;
	private String startime;
	private String endtime;

	public String getToken() {
		return token;
	}

	public String getStaffid() {
		return staffid;
	}

	public String getLangcode() {
		return langcode;
	}

	public String getRoomno() {
		return roomno;
	}

	public int getOrderid() {
		return orderid;
	}

	public String getStatus() {
		return status;
	}

	public String getStartime() {
		return startime;
	}

	public String getEndtime() {
		return endtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceBackendOrderListRequest [token=");
		builder.append(token);
		builder.append(", staffid=");
		builder.append(staffid);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", startime=");
		builder.append(startime);
		builder.append(", endtime=");
		builder.append(endtime);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceBackendOrderListRequest(String token, String staffid, String langcode, String roomno, int orderid,
			String status, String startime, String endtime) {
		super();
		this.token = token;
		this.staffid = staffid;
		this.langcode = langcode;
		this.roomno = roomno;
		this.orderid = orderid;
		this.status = status;
		this.startime = startime;
		this.endtime = endtime;
	}

}
