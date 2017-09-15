/**
 * 
 */
package com.sidc.utils.log;

import java.io.Serializable;

import org.slf4j.Logger;

/**
 * @author Nandin
 *
 */
public class SenderRcuLogBean implements Serializable {

	private static final long serialVersionUID = -8042234160340768175L;
	private Logger logger;
	private String id;
	private String keycode;
	private String roomno;
	private long starttime;
	private String performance = "0";

	public SenderRcuLogBean(Logger logger) {
		super();
		this.logger = logger;
		this.starttime = System.currentTimeMillis();
	}

	public Logger getLogger() {
		return logger;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public long getStarttime() {
		return starttime;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getKeycode() {
		return keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append("|");
		builder.append(roomno);
		builder.append("|");
		builder.append(keycode);
		builder.append("|");
		builder.append(this.performance);
		builder.append("|");

		return builder.toString();
	}

}
