package com.sidc.blackcore.api.sits.shop.request;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Allen Huang
 *
 */
public class ShoppingListRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3999802897567535441L;
	private String roomno;
	private String publickey;
	private String privatekey;
	private List<ShoppingRequest> list;
	
	public ShoppingListRequest(String roomno, String publickey, String privatekey, List<ShoppingRequest> list) {
		super();
		this.roomno = roomno;
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.list = list;
	}

	public String getRoomno() {
		return roomno;
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
		builder.append("ShoppingListRequest [roomno=");
		builder.append(roomno);
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
