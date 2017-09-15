/**
 * 
 */
package com.sidc.blackcore.api.ra.response;

/**
 * @author Nandin
 *
 */
public class RaUDPEnity implements java.io.Serializable {

	private static final long serialVersionUID = -3864536644585020205L;

	private String address;
	private int sendport;
	private int recport;

	public RaUDPEnity(String address, int sendport, int recport) {
		super();
		this.address = address;
		this.sendport = sendport;
		this.recport = recport;
	}

	public String getAddress() {
		return address;
	}

	public int getSendport() {
		return sendport;
	}

	public int getRecport() {
		return recport;
	}

}
