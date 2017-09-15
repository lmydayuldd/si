package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopOrderItemBean;

public class ShoppingOrderCreateRequest implements Serializable {
	private static final long serialVersionUID = 4825730183137915708L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String guestno;
	private String guestfirstname;
	private String guestlastname;
	private String memo;
	private List<ShopOrderItemBean> itemlist = new ArrayList<ShopOrderItemBean>();

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

	public List<ShopOrderItemBean> getItemlist() {
		return itemlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingOrderCreateRequest [publickey=");
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

	public ShoppingOrderCreateRequest(String publickey, String privatekey, String roomno, String guestno,
			String guestfirstname, String guestlastname, String memo, List<ShopOrderItemBean> itemlist) {
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