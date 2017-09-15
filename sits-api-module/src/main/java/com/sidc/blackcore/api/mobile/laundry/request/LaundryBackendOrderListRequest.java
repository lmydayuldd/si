package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;

public class LaundryBackendOrderListRequest implements Serializable {
	private static final long serialVersionUID = -1399073023541469529L;
	private String token;
	private String staffid;
	private String langcode;
	private String roomno;
	private int orderid;
	private int classid;
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

	public int getClassid() {
		return classid;
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
		builder.append("LaundryBackendOrderListRequest [token=");
		builder.append(token);
		builder.append(", staffid=");
		builder.append(staffid);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", classid=");
		builder.append(classid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", startime=");
		builder.append(startime);
		builder.append(", endtime=");
		builder.append(endtime);
		builder.append("]");
		return builder.toString();
	}

	public LaundryBackendOrderListRequest(String token, String staffid, String langcode, String roomno, int orderid,
			int classid, String status, String startime, String endtime) {
		super();
		this.token = token;
		this.staffid = staffid;
		this.langcode = langcode;
		this.roomno = roomno;
		this.orderid = orderid;
		this.classid = classid;
		this.status = status;
		this.startime = startime;
		this.endtime = endtime;
	}

}
