package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryOrderItemBean;

public class LaundryOrderCreateRequest implements Serializable {
	private static final long serialVersionUID = 6205343021544325956L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String guestno;
	private String guestfirstname;
	private String guestlastname;
	private String receivetime;
	private int classid;
	private String memo;
	private List<LaundryOrderItemBean> itemlist = new ArrayList<LaundryOrderItemBean>();

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

	public String getReceivetime() {
		return receivetime;
	}

	public int getClassid() {
		return classid;
	}

	public String getMemo() {
		return memo;
	}

	public List<LaundryOrderItemBean> getItemlist() {
		return itemlist;
	}

	public LaundryOrderCreateRequest(String publickey, String privatekey, String roomno, String guestno,
			String guestfirstname, String guestlastname, String receivetime, int classid, String memo,
			List<LaundryOrderItemBean> itemlist) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.guestno = guestno;
		this.guestfirstname = guestfirstname;
		this.guestlastname = guestlastname;
		this.receivetime = receivetime;
		this.classid = classid;
		this.memo = memo;
		this.itemlist = itemlist;
	}

}
