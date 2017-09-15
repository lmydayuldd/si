/**
 * 
 */
package com.sidc.rcu.connector.bean.receiver;

/**
 * @author Nandin
 * 
 */
public class CardReceiver implements java.io.Serializable {

	private static final long serialVersionUID = -3180487938525537096L;

	private int status;
	private int authorization;
	private String keycode;
	public CardReceiver(int status, int authorization, String keycode) {
		this.status = status;
		this.authorization = authorization;
		this.keycode = keycode;
	}
	public int getStatus() {
		return status;
	}
	public int getAuthorization() {
		return authorization;
	}
	public String getKeycode() {
		return keycode;
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("CardReceiver [status=\n");
		builder.append(status);
		builder.append(", authorization=\n");
		builder.append(authorization);
		builder.append(", keycode=\n");
		builder.append(keycode);
		builder.append("]");
		return builder.toString();
	}
}
