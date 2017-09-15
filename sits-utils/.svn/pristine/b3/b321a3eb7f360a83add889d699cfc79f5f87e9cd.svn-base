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
public class ReceiverRcuLogBean implements Serializable {

	private static final long serialVersionUID = -8042234160340768175L;
	private Logger logger;
	private String id;
	private String catalogue;
	private String roomno;
	private String roomip;
	private String content;
	private long starttime;
	private String performance = "0";

	public ReceiverRcuLogBean(Logger logger) {
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

	public String getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
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

	public String getRoomip() {
		return roomip;
	}

	public void setRoomip(String roomip) {
		this.roomip = roomip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append("|");
		builder.append(roomno);
		builder.append("|");
		builder.append(roomip);
		builder.append("|");
		builder.append(catalogue);
		builder.append("|");
		builder.append(this.content);
		builder.append("|");
		builder.append(this.performance);
		builder.append("|");

		return builder.toString();
	}

}
