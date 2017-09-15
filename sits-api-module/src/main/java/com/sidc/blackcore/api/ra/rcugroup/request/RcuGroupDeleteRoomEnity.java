/**
 * 
 */
package com.sidc.blackcore.api.ra.rcugroup.request;

/**
 * @author Nandin
 *
 */
public class RcuGroupDeleteRoomEnity implements java.io.Serializable {

	private static final long serialVersionUID = -7677558024119591443L;
	private String roomno;

	public RcuGroupDeleteRoomEnity(String roomno) {
		super();
		this.roomno = roomno;
	}

	public String getRoomno() {
		return roomno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupDeleteRoomEnity [\n\troomno=");
		builder.append(roomno);
		builder.append("]");
		return builder.toString();
	}

}
