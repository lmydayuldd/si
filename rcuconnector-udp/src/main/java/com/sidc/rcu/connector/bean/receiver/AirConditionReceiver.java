package com.sidc.rcu.connector.bean.receiver;

/**
 * @author Nandin
 *
 */
public class AirConditionReceiver implements java.io.Serializable {

	private static final long serialVersionUID = 8306883313310798881L;

	private int status;
	private int mode;
	private int temperature;
	private int speed;
	private int actualTemp;
	private int isAirConditioning;
	private int position;

	public AirConditionReceiver(int status, int mode, int temperature, int speed, int actualTemp,
			int isAirConditioning, int position) {
		this.status = status;
		this.mode = mode;
		this.temperature = temperature;
		this.speed = speed;
		this.actualTemp = actualTemp;
		this.isAirConditioning = isAirConditioning;
		this.position = position;
	}

	public int isStatus() {
		return status;
	}

	public int getMode() {
		return mode;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getSpeed() {
		return speed;
	}

	public int getActualTemp() {
		return actualTemp;
	}

	public int isAirConditioning() {
		return isAirConditioning;
	}

	public int getPosition() {
		return position;
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("AirConditonReceiver [status=\n");
		builder.append(status);
		builder.append(", mode=\n");
		builder.append(mode);
		builder.append(", temperature=\n");
		builder.append(temperature);
		builder.append(", speed=\n");
		builder.append(speed);
		builder.append(", actualTemp=\n");
		builder.append(actualTemp);
		builder.append(", isAirConditioning=\n");
		builder.append(isAirConditioning);
		builder.append(", position=\n");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}
}
