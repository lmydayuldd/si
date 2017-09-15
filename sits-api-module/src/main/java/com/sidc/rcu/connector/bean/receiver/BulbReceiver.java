/**
 * 
 */
package com.sidc.rcu.connector.bean.receiver;

/**
 * @author Nandin
 *
 */
public class BulbReceiver implements java.io.Serializable {

	private static final long serialVersionUID = -7965363203592047163L;
	private String keycode;
	private int status;

	/**
	 * @param uuid
	 * @param roomNo
	 */
	public BulbReceiver(String keycode, int status) {
		this.keycode = keycode;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public String getKeycode() {
		return keycode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("keycode=");
		builder.append(keycode);
		builder.append(", tstatus=");
		builder.append(status);
		return builder.toString();
	}

}
