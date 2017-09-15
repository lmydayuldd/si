/**
 * 
 */
package com.sidc.blackcore.api.sits.account.request;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class SystemUserInsertRequest implements Serializable {
	private static final long serialVersionUID = -3345229312879407341L;
	private String id;
	private String password;
	private String name;
	private String email;
	private String staffcode;

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getStaffcode() {
		return staffcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemUserInsertRequest [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", staffcode=");
		builder.append(staffcode);
		builder.append("]");
		return builder.toString();
	}

	public SystemUserInsertRequest(String id, String password, String name, String email, String staffcode) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.staffcode = staffcode;
	}

}
