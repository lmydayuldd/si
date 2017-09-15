package com.sidc.blackcore.api.mobile.activity.bean;

import java.io.Serializable;

public class ActivitySignUpBean implements Serializable {
	private static final long serialVersionUID = 8832039438583921399L;
	private int feeid;
	private String firstname;
	private String lastname;
	private int sex;
	private String identitytype;
	private String identity;
	private String passport;
	private String phone;
	private String email;
	private String guestno;

	public int getFeeid() {
		return feeid;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getSex() {
		return sex;
	}

	public String getIdentitytype() {
		return identitytype;
	}

	public String getIdentity() {
		return identity;
	}

	public String getPassport() {
		return passport;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getGuestno() {
		return guestno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivitySignUpBean [feeid=");
		builder.append(feeid);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", identitytype=");
		builder.append(identitytype);
		builder.append(", identity=");
		builder.append(identity);
		builder.append(", passport=");
		builder.append(passport);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", email=");
		builder.append(email);
		builder.append(", guestno=");
		builder.append(guestno);
		builder.append("]");
		return builder.toString();
	}

	public ActivitySignUpBean(int feeid, String firstname, String lastname, int sex, String identitytype,
			String identity, String passport, String phone, String email, String guestno) {
		super();
		this.feeid = feeid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
		this.identitytype = identitytype;
		this.identity = identity;
		this.passport = passport;
		this.phone = phone;
		this.email = email;
		this.guestno = guestno;
	}

}
