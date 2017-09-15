package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;

public class LaundryOrderListRequest implements Serializable {
	private static final long serialVersionUID = 3828070636367430477L;
	private String langcode;
	private String deviceid;
	private int orderid;
	private int classid;
	private String status;
	private String startime;
	private String endtime;

	public String getLangcode() {
		return langcode;
	}

	public String getDeviceid() {
		return deviceid;
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

	public int getClassid() {
		return classid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryOrderListRequest [langcode=");
		builder.append(langcode);
		builder.append(", deviceid=");
		builder.append(deviceid);
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

	public LaundryOrderListRequest(String langcode, String deviceid, int orderid, int classid, String status,
			String startime, String endtime) {
		super();
		this.langcode = langcode;
		this.deviceid = deviceid;
		this.orderid = orderid;
		this.classid = classid;
		this.status = status;
		this.startime = startime;
		this.endtime = endtime;
	}

}
