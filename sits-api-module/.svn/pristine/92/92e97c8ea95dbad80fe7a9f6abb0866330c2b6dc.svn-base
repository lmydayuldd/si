package com.sidc.blackcore.api.sits.bill.request;

import java.io.Serializable;

/**
 * 
 * @author Allen Huang
 *
 */
public class BillReviewRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 269193705329915554L;
	private String publickey;
	private String privatekey;
	private String roomno;
	private String guestno;
	private String langcode;
	
	public BillReviewRequest(String publickey, String privatekey, String roomno, String guestno, String langcode) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.roomno = roomno;
		this.guestno = guestno;
		this.langcode = langcode;
	}

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

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BillReviewRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", guestno=");
		builder.append(guestno);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}
	
}
