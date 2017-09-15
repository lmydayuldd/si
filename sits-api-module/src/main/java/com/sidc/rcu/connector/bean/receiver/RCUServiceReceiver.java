/**
 * 
 */
package com.sidc.rcu.connector.bean.receiver;

/**
 * @author Nandin
 *
 */
public class RCUServiceReceiver implements java.io.Serializable {

	private static final long serialVersionUID = 1079443368177722421L;
	private String keycode;
	private int status;
	public RCUServiceReceiver(String keycode, int status) {
		this.keycode = keycode;
		this.status = status;
	}
	
	public String getKeycode() {
		return keycode;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("keycode=");
		builder.append(keycode);
		builder.append(", status=");
		builder.append(status);
		return builder.toString();
	}

}
