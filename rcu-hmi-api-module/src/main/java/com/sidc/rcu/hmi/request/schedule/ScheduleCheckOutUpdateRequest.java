package com.sidc.rcu.hmi.request.schedule;

import java.io.Serializable;

public class ScheduleCheckOutUpdateRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -48184686391432259L;
	private boolean openswitch;
	private int function;
	private int temperature;
	private String starttime;
	private int delayclosetime;

	public boolean isOpenswitch() {
		return openswitch;
	}

	public int getFunction() {
		return function;
	}

	public int getTemperature() {
		return temperature;
	}

	public String getStarttime() {
		return starttime;
	}

	public int getDelayclosetime() {
		return delayclosetime;
	}

	public void setDelayclosetime(int delayclosetime) {
		this.delayclosetime = delayclosetime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleCheckOutUpdateRequest [openswitch=");
		builder.append(openswitch);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", starttime=");
		builder.append(starttime);
		builder.append(", delayclosetime=");
		builder.append(delayclosetime);
		builder.append("]");
		return builder.toString();
	}

	public ScheduleCheckOutUpdateRequest(boolean openswitch, int function, int temperature, String starttime,
			int delayclosetime) {
		super();
		this.openswitch = openswitch;
		this.function = function;
		this.temperature = temperature;
		this.starttime = starttime;
		this.delayclosetime = delayclosetime;
	}
}
