package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderSetBean;

public class RoomServiceCreateOrderRequest implements Serializable {
	private static final long serialVersionUID = 6643109707584693683L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String guestno;
	private String guestfirstname;
	private String guestlastname;
	private String expectedtime;
	private String memo;
	private List<RoomServiceOrderLineBean> list = new ArrayList<RoomServiceOrderLineBean>();
	private List<RoomServiceOrderSetBean> setlist = new ArrayList<RoomServiceOrderSetBean>();

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getGuestno() {
		return guestno;
	}

	public String getGuestfirstname() {
		return guestfirstname;
	}

	public String getGuestlastname() {
		return guestlastname;
	}

	public String getMemo() {
		return memo;
	}

	public List<RoomServiceOrderLineBean> getList() {
		return list;
	}

	public List<RoomServiceOrderSetBean> getSetlist() {
		return setlist;
	}

	public void setList(List<RoomServiceOrderLineBean> list) {
		this.list = list;
	}

	public void setSetlist(List<RoomServiceOrderSetBean> setlist) {
		this.setlist = setlist;
	}

	public String getExpectedtime() {
		return expectedtime;
	}

	public void setExpectedtime(String expectedtime) {
		this.expectedtime = expectedtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceCreateOrderRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", guestno=");
		builder.append(guestno);
		builder.append(", guestfirstname=");
		builder.append(guestfirstname);
		builder.append(", guestlastname=");
		builder.append(guestlastname);
		builder.append(", expectedtime=");
		builder.append(expectedtime);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", list=");
		builder.append(list);
		builder.append(", setlist=");
		builder.append(setlist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceCreateOrderRequest(String publickey, String privatekey, String roomno, String guestno,
			String guestfirstname, String guestlastname, String expectedtime, String memo,
			List<RoomServiceOrderLineBean> list, List<RoomServiceOrderSetBean> setlist) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.guestno = guestno;
		this.guestfirstname = guestfirstname;
		this.guestlastname = guestlastname;
		this.expectedtime = expectedtime;
		this.memo = memo;
		this.list = list;
		this.setlist = setlist;
	}

}
