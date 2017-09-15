package com.sidc.blackcore.api.sits.spare.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.spare.bean.SpareOrderItemBean;

public class SpareOrderCreateRequest implements Serializable {
	private static final long serialVersionUID = 7874637502279656976L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String guestno;
	private String guestfirstname;
	private String guestlastname;
	private String memo;
	private List<SpareOrderItemBean> itemlist = new ArrayList<SpareOrderItemBean>();

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

	public List<SpareOrderItemBean> getItemlist() {
		return itemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareOrderCreateRequest [publickey=");
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
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", itemlist=");
		builder.append(itemlist);
		builder.append("]");
		return builder.toString();
	}

	public SpareOrderCreateRequest(String publickey, String privatekey, String roomno, String guestno,
			String guestfirstname, String guestlastname, String memo, List<SpareOrderItemBean> itemlist) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.guestno = guestno;
		this.guestfirstname = guestfirstname;
		this.guestlastname = guestlastname;
		this.memo = memo;
		this.itemlist = itemlist;
	}

}