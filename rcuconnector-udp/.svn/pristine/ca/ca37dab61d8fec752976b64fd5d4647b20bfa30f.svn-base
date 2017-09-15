/**
 * 
 */
package com.sidc.rcu.connector.bean.command;

/**
 * @author Nandin
 *
 */
public class AirConditionCommander implements java.io.Serializable {

	private static final long serialVersionUID = -7778234335493936465L;

	private boolean isPower;
	private int function;
	private int temperature;
	private int speed;
	private int timer;
	private byte address;

	public AirConditionCommander(byte address) {
		this.address = address;
	}

	public boolean isPower() {
		return isPower;
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

	public byte getAddress() {
		return address;
	}

	public void setPower(boolean isPower) {
		this.isPower = isPower;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AirConditionCommand [\n\tisPower=");
		builder.append(isPower);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", timer=");
		builder.append(timer);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
