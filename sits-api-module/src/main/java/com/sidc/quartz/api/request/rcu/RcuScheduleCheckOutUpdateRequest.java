package com.sidc.quartz.api.request.rcu;

import java.io.Serializable;

public class RcuScheduleCheckOutUpdateRequest implements Serializable {
	private static final long serialVersionUID = -8042447006459228856L;
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

	public RcuScheduleCheckOutUpdateRequest(boolean openswitch, int function, int temperature, String starttime,
			int delayclosetime) {
		super();
		this.openswitch = openswitch;
		this.function = function;
		this.temperature = temperature;
		this.starttime = starttime;
		this.delayclosetime = delayclosetime;
	}

}
