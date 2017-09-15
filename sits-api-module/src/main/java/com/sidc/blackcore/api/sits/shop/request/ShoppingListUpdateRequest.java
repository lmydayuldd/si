package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Allen Huang
 *
 */
public class ShoppingListUpdateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3877110541801032109L;
	private String roomno;
	private String orderid;
	private String publickey;
	private String privatekey;
	private List<ShoppingRequest> list;
	
	public ShoppingListUpdateRequest(String roomno, String orderid, String publickey, String privatekey, List<ShoppingRequest> list) {
		super();
		this.roomno = roomno;
		this.orderid = orderid;
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.list = list;
	}
	
	public String getRoomno() {
		return roomno;
	}

	public String getOrderid() {
		return orderid;
	}

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public List<ShoppingRequest> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingListUpdateRequest [roomno=");
		builder.append(roomno);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
	
}
