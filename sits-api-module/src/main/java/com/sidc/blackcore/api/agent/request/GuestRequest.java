/**
 * 
 */
package com.sidc.blackcore.api.agent.request;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class GuestRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4086518608567946330L;
	private String guestno;
	private String firstname;
	private String lastname;
	private String birthd;
	private String depdate;
	private String groupid;
	private String salutation;

	public GuestRequest(String guestno, String firstname, String lastname, String birthd, String depdate,
			String groupid, String salutation) {
		super();
		this.guestno = guestno;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthd = birthd;
		this.depdate = depdate;
		this.groupid = groupid;
		this.salutation = salutation;
	}

	public String getGuestno() {
		return guestno;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getBirthd() {
		return birthd;
	}

	public String getDepdate() {
		return depdate;
	}

	public String getGroupid() {
		return groupid;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setDepdate(String depdate) {
		this.depdate = depdate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuestRequest [guestno=\n");
		builder.append(guestno);
		builder.append(", firstname=\n");
		builder.append(firstname);
		builder.append(", lastname=\n");
		builder.append(lastname);
		builder.append(", birthd=\n");
		builder.append(birthd);
		builder.append(", depdate=\n");
		builder.append(depdate);
		builder.append(", groupid=\n");
		builder.append(groupid);
		builder.append(", salutation=\n");
		builder.append(salutation);
		builder.append("]");
		return builder.toString();
	}

}
