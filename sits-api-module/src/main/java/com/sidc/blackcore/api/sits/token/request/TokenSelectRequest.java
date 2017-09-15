/**
 * 
 */
package com.sidc.blackcore.api.sits.token.request;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class TokenSelectRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4495100339230865669L;
	private String roomno;
	private String stbip;

	public String getRoomno() {
		return roomno;
	}

	public String getStbip() {
		return stbip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenSelectRequest [roomno=");
		builder.append(roomno);
		builder.append(", stbip=");
		builder.append(stbip);
		builder.append("]");
		return builder.toString();
	}

	public TokenSelectRequest(String roomno, String stbip) {
		super();
		this.roomno = roomno;
		this.stbip = stbip;
	}
}
