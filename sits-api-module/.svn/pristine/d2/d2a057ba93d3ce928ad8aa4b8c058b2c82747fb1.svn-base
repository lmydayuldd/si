package com.sidc.blackcore.api.agent.request;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class RoomChangeRequest implements Serializable {

	private static final long serialVersionUID = 2570653725229768048L;

	private String oldRoomNumber;
	private String newRoomNumber;
	private CheckInRequest checkInEntity;

	public CheckInRequest getCheckInEntity() {
		return checkInEntity;
	}

	public void setCheckInEntity(CheckInRequest checkInEntity) {
		this.checkInEntity = checkInEntity;
	}

	public RoomChangeRequest(String oldRoomNumber, String newRoomNumber) {
		super();
		this.oldRoomNumber = oldRoomNumber;
		this.newRoomNumber = newRoomNumber;
	}

	public String getOldRoomNumber() {
		return oldRoomNumber;
	}

	public String getNewRoomNumber() {
		return newRoomNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomChangeRequest [oldRoomNumber=");
		builder.append(oldRoomNumber);
		builder.append(", newRoomNumber=");
		builder.append(newRoomNumber);
		builder.append(", checkInEntity=");
		builder.append(checkInEntity);
		builder.append("]");
		return builder.toString();
	}

	public RoomChangeRequest(String oldRoomNumber, String newRoomNumber, CheckInRequest checkInEntity) {
		super();
		this.oldRoomNumber = oldRoomNumber;
		this.newRoomNumber = newRoomNumber;
		this.checkInEntity = checkInEntity;
	}
}
