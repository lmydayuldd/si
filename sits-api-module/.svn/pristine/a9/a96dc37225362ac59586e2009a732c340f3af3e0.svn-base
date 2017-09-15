package com.sidc.blackcore.api.sits.roomallocation.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomallocation.bean.RoomListBean;

public class HandlerTransferRequest implements Serializable {
	private static final long serialVersionUID = -3599229124807589855L;
	private String token;
	private String userid;
	private List<RoomListBean> roomlist = new ArrayList<RoomListBean>();
	private String targetid;

	public String getToken() {
		return token;
	}

	public String getUserid() {
		return userid;
	}

	public List<RoomListBean> getRoomlist() {
		return roomlist;
	}

	public String getTargetid() {
		return targetid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HandlerTransferRequest [token=");
		builder.append(token);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", roomlist=");
		builder.append(roomlist);
		builder.append(", targetid=");
		builder.append(targetid);
		builder.append("]");
		return builder.toString();
	}

	public HandlerTransferRequest(String token, String userid, List<RoomListBean> roomlist, String targetid) {
		super();
		this.token = token;
		this.userid = userid;
		this.roomlist = roomlist;
		this.targetid = targetid;
	}

}
