package com.sidc.dao.sits.bean;

import java.io.Serializable;
import java.util.Date;

public class GuestBean implements Serializable{
	private static final long serialVersionUID = 5693240882407086976L;
	
	private String id;	
	private String guest_no;
	private String bill_no;
	private String room_no;
	private String en_first_name;
	private String en_last_name;
	private short birth_month;
	private short birth_day;
	private String tel;
	private String email;
	private String national="English";
	private String addr;
	private String note;
	private Date last_modify;
	private String modify_name;
	private Character modify;
	private String group_id;
	private String salutation;
	
	public GuestBean(){
		super();
	}	
	
	public String getId() {
		return id;
	}
	public String getGuest_no() {
		return guest_no;
	}
	public String getBill_no() {
		return bill_no;
	}
	public String getRoom_no() {
		return room_no;
	}
	public String getEn_first_name() {
		return en_first_name;
	}
	public String getEn_last_name() {
		return en_last_name;
	}
	public short getBirth_month() {
		return birth_month;
	}
	public short getBirth_day() {
		return birth_day;
	}
	public String getTel() {
		return tel;
	}
	public String getEmail() {
		return email;
	}
	public String getNational() {
		return national;
	}
	public String getAddr() {
		return addr;
	}
	public String getNote() {
		return note;
	}
	public Date getLast_modify() {
		return last_modify;
	}
	public String getModify_name() {
		return modify_name;
	}
	public Character getModify() {
		return modify;
	}
	public String getGroup_id() {
		return group_id;
	}
	public String getSalutation() {
		return salutation;
	}

}
