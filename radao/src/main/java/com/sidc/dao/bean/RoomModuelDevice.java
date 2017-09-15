/**
 * 
 */
package com.sidc.dao.bean;

/**
 * @author Nandin
 *
 */
public class RoomModuelDevice implements java.io.Serializable {

	private static final long serialVersionUID = -7458927815596638267L;

	private int groupId;
	private String groupName;
	private int deviceId;

	public RoomModuelDevice(int groupId, String groupName, int deviceId) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.deviceId = deviceId;
	}

	public int getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public int getDeviceId() {
		return deviceId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomModuelDevice [\n\tgroupId=");
		builder.append(groupId);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", deviceId=");
		builder.append(deviceId);
		builder.append("]");
		return builder.toString();
	}

}
