/**
 * 
 */
package com.sidc.dao.sits.bean;

import java.io.Serializable;

import com.sidc.dao.sits.room.Room;

/**
 * @author Joe
 *
 */
public class RoomBean implements Serializable {
	private static final long serialVersionUID = -7183920356283540047L;
	
	private String no;
	private String bill_no = null;
	private short floor;
	private String building;
	private int type = Room.NORMAL_TYPE;
	private int newMsg;
	private short welcomeMsg = Room.WELCOME_FIRST_TIME;
	private short parentControl = Room.UNLOCK;
	private String folio;
	private short pay_service = Room.PAY_ON;
	private String p_password;
	private String adult_warning = Room.UNWARN;;
	private boolean messageLight = Boolean.TRUE;
	private String av_passcode;
	private String block_code;

	
	public RoomBean(){
		super();
	}
	
	public RoomBean(final int newMsg){
		this.newMsg = newMsg;
	}
	
	public String getNo() {
		return no;
	}
	public String getBill_no() {
		return bill_no;
	}
	public short getFloor() {
		return floor;
	}
	public String getBuilding() {
		return building;
	}
	public int getType() {
		return type;
	}
	public int getNewMsg() {
		return newMsg;
	}
	public short getWelcomeMsg() {
		return welcomeMsg;
	}
	public short getParentControl() {
		return parentControl;
	}
	public String getFolio() {
		return folio;
	}
	public short getPay_service() {
		return pay_service;
	}
	public String getP_password() {
		return p_password;
	}
	public String getAdult_warning() {
		return adult_warning;
	}
	public boolean isMessageLight() {
		return messageLight;
	}
	public String getAv_passcode() {
		return av_passcode;
	}
	public String getBlock_code() {
		return block_code;
	}

}
