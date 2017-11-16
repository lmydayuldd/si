package com.sidc.blackcore.api.ra.rcumodesetting.request;

public class RcuRoomModuleUpdateRequest implements java.io.Serializable {
	private static final long serialVersionUID = 4857230594140439121L;

	private String roomno;
	private int groupid;

	public String getRoomno() {
		return roomno;
	}

	public int getGroupid() {
		return groupid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuRoomModuleUpdateRequest [roomno=");
		builder.append(roomno);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append("]");
		return builder.toString();
	}

	public RcuRoomModuleUpdateRequest(String roomno, int groupid) {
		super();
		this.roomno = roomno;
		this.groupid = groupid;
	}

}
