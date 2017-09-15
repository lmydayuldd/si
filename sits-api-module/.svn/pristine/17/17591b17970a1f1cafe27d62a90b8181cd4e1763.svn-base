/**
 * 
 */
package com.sidc.blackcore.api.sits.roomallocation.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomallocation.bean.RoomListBean;

public class RoomAllocationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224932834122343993L;
	private String token;
	private String userid;
	private List<RoomListBean> roomlist = new ArrayList<RoomListBean>();

	public String getToken() {
		return token;
	}

	public String getUserid() {
		return userid;
	}

	public List<RoomListBean> getRoomlist() {
		return roomlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomAllocationRequest [token=");
		builder.append(token);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", roomlist=");
		builder.append(roomlist);
		builder.append("]");
		return builder.toString();
	}

	public RoomAllocationRequest(String token, String userid, List<RoomListBean> roomlist) {
		super();
		this.token = token;
		this.userid = userid;
		this.roomlist = roomlist;
	}

}
