/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;

public class ModeInitialDevicesInsertBean implements Serializable {
	private static final long serialVersionUID = -7508415593341504727L;
	private int id;
	private String gouprname;
	private String keycode;
	private int status;
	private boolean ispower;
	private int function;
	private int temperature;
	private int speed;
	private int timer;

	public int getId() {
		return id;
	}

	public String getGouprname() {
		return gouprname;
	}

	public String getKeycode() {
		return keycode;
	}

	public int getStatus() {
		return status;
	}

	public boolean isPower() {
		return ispower;
	}

	public int getFunction() {
		return function;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getSpeed() {
		return speed;
	}

	public int getTimer() {
		return timer;
	}

	public ModeInitialDevicesInsertBean(int id, String gouprname, String keycode, int status, boolean ispower,
			int function, int temperature, int speed, int timer) {
		super();
		this.id = id;
		this.gouprname = gouprname;
		this.keycode = keycode;
		this.status = status;
		this.ispower = ispower;
		this.function = function;
		this.temperature = temperature;
		this.speed = speed;
		this.timer = timer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialDevicesInsertBean [id=");
		builder.append(id);
		builder.append(", gouprname=");
		builder.append(gouprname);
		builder.append(", keycode=");
		builder.append(keycode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", ispower=");
		builder.append(ispower);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", timer=");
		builder.append(timer);
		builder.append("]");
		return builder.toString();
	}

}
