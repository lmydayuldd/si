package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightAwareTimeBean implements Serializable {
	private static final long serialVersionUID = 3470958694155539647L;
	private long epoch;
	private String tz;
	private String dow;
	private String time;
	private String date;
	private long localtime;

	public long getEpoch() {
		return epoch;
	}

	public String getTz() {
		return tz;
	}

	public String getDow() {
		return dow;
	}

	public String getTime() {
		return time;
	}

	public String getDate() {
		return date;
	}

	public long getLocaltime() {
		return localtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightAwareTimeBean [epoch=");
		builder.append(epoch);
		builder.append(", tz=");
		builder.append(tz);
		builder.append(", dow=");
		builder.append(dow);
		builder.append(", time=");
		builder.append(time);
		builder.append(", date=");
		builder.append(date);
		builder.append(", localtime=");
		builder.append(localtime);
		builder.append("]");
		return builder.toString();
	}

	public FlightAwareTimeBean(long epoch, String tz, String dow, String time, String date, long localtime) {
		super();
		this.epoch = epoch;
		this.tz = tz;
		this.dow = dow;
		this.time = time;
		this.date = date;
		this.localtime = localtime;
	}

}
