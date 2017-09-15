package com.sidc.blackcore.api.agent.request;

import java.io.Serializable;

public class AgentPostRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7030122088211946022L;
	private String status;
	private String roomno;
	private String agentid;

	public AgentPostRequest(String status, String roomno, String agentid) {
		super();
		this.status = status;
		this.roomno = roomno;
		this.agentid = agentid;
	}

	public String getStatus() {
		return status;
	}

	public String getAgentid() {
		return agentid;
	}

	public String getRoomno() {
		return roomno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AgentPostRequest [\n\tstatus=");
		builder.append(status);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", agentid=");
		builder.append(agentid);
		builder.append("]");
		return builder.toString();
	}
}
