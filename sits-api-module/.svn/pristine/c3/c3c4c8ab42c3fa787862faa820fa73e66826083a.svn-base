package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;

/**
 * 
 * @author Allen Huang
 *
 */
public class ShoppingDeleteRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4604769106040318909L;
	private String roomno;
	private String orderid;
	private String publickey;
	private String privatekey;
	
	public ShoppingDeleteRequest(String roomno, String orderid, String publickey, String privatekey) {
		super();
		this.roomno = roomno;
		this.orderid = orderid;
		this.publickey = publickey;
		this.privatekey = privatekey;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingDeleteRequest [roomno=");
		builder.append(roomno);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append("]");
		return builder.toString();
	}
	
}
