package com.sidc.rcu.hmi.rcucommand.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HvacControlRequest implements Serializable {
	private static final long serialVersionUID = -5512062678749128190L;
	private int function;
	private int temperature;
	private int delayclosetime;
	private String type;
	private List<String> itemlist = new ArrayList<String>();

	public int getFunction() {
		return function;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getDelayclosetime() {
		return delayclosetime;
	}

	public String getType() {
		return type;
	}

	public List<String> getItemlist() {
		return itemlist;
	}

	public void setDelayclosetime(int delayclosetime) {
		this.delayclosetime = delayclosetime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HvacControlRequest [function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", delayclosetime=");
		builder.append(delayclosetime);
		builder.append(", type=");
		builder.append(type);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public HvacControlRequest(int function, int temperature, int delayclosetime, String type, List<String> itemlist) {
		super();
		this.function = function;
		this.temperature = temperature;
		this.delayclosetime = delayclosetime;
		this.type = type;
		this.itemlist = itemlist;
	}

}
