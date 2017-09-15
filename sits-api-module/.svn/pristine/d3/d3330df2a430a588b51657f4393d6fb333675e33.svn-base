/**
 * 
 */
package com.derex.cm.stb.api.response;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class StbStatusResponse implements Serializable {

	private static final long serialVersionUID = 402551886280058134L;
	private String roomno;
	private String billno;
	private String ip;
	private String wifi;
	private boolean registed;
	private int floor;
	private boolean status;

	public StbStatusResponse(String roomno, String billno, String ip, String wifi, boolean registed, int floor,
			boolean status) {
		super();
		this.roomno = roomno;
		this.billno = billno;
		this.ip = ip;
		this.wifi = wifi;
		this.registed = registed;
		this.floor = floor;
		this.status = status;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getBillno() {
		return billno;
	}

	public String getIp() {
		return ip;
	}

	public String getWifi() {
		return wifi;
	}

	public boolean isRegisted() {
		return registed;
	}

	public int getFloor() {
		return floor;
	}

	public boolean getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbStatusResponse [roomno=\n");
		builder.append(roomno);
		builder.append(", billno=\n");
		builder.append(billno);
		builder.append(", ip=\n");
		builder.append(ip);
		builder.append(", wifi=\n");
		builder.append(wifi);
		builder.append(", registed=\n");
		builder.append(registed);
		builder.append(", floor=\n");
		builder.append(floor);
		builder.append(", status=\n");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
