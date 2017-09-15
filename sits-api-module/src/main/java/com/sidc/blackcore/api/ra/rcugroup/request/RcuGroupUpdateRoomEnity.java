/**
 * 
 */
package com.sidc.blackcore.api.ra.rcugroup.request;

/**
 * @author Nandin
 *
 */
public class RcuGroupUpdateRoomEnity implements java.io.Serializable {

	private static final long serialVersionUID = -1537996870284300608L;
	private String roomno;
	private int rcugroupid;

	public RcuGroupUpdateRoomEnity(String roomno, int rcugroupid) {
		super();
		this.roomno = roomno;
		this.rcugroupid = rcugroupid;
	}

	public String getRoomno() {
		return roomno;
	}

	public int getRcugroupid() {
		return rcugroupid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuGroupNewRoomEnity [\n\troomno=");
		builder.append(roomno);
		builder.append(", rcugroupid=");
		builder.append(rcugroupid);
		builder.append("]");
		return builder.toString();
	}

}
