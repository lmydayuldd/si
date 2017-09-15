/**
 * 
 */
package com.sidc.rcu.connector.bean.receiver;

/**
 * @author Nandin
 *
 */
public class PMSReceiver implements java.io.Serializable {

	private static final long serialVersionUID = -3684331041936332156L;
	private String keycode;

	/**
	 * @param uuid
	 * @param roomNo
	 */
	public PMSReceiver(String keycode) {
		this.keycode = keycode;
	}

	public String getKeycode() {
		return keycode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PMSReceiver [\n\tkeycode=");
		builder.append(keycode);
		builder.append("]");
		return builder.toString();
	}

}
