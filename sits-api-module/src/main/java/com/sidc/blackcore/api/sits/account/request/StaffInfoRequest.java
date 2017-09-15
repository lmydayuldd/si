/**
 * 
 */
package com.sidc.blackcore.api.sits.account.request;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class StaffInfoRequest implements Serializable {
	private static final long serialVersionUID = 240845904996401304L;
	private String token;
	private String staffid;

	public String getToken() {
		return token;
	}

	public String getStaffid() {
		return staffid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StaffInfoRequest [token=");
		builder.append(token);
		builder.append(", staffid=");
		builder.append(staffid);
		builder.append("]");
		return builder.toString();
	}

	public StaffInfoRequest(String token, String staffid) {
		super();
		this.token = token;
		this.staffid = staffid;
	}

}
