/**
 * 
 */
package com.sidc.blackcore.api.ra.rcugroup.request;

/**
 * @author Nandin
 *
 */
public class RcuGroupNewRoomEnity implements java.io.Serializable {

	private static final long serialVersionUID = -1316808490666793604L;
	private String roomno;
	private int rcugroupid;

	public RcuGroupNewRoomEnity(String roomno, int rcugroupid) {
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
