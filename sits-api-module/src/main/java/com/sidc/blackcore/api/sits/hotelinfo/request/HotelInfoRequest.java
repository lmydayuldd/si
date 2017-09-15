/**
 * 
 */
package com.sidc.blackcore.api.sits.hotelinfo.request;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class HotelInfoRequest implements Serializable {
	private static final long serialVersionUID = -9126636148376632449L;
	private String signature;

	public String getSignature() {
		return signature;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HotelInfoRequest [signature=");
		builder.append(signature);
		builder.append("]");
		return builder.toString();
	}

	public HotelInfoRequest(String signature) {
		super();
		this.signature = signature;
	}

}
