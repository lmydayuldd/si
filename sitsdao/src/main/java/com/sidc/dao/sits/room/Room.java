package com.sidc.dao.sits.room;

/**
 * 
 * @author Allen Huang
 *
 */
public interface Room {

	public static final int NORMAL_TYPE = 1;
	public static final int PREFERENCE_TYPE = 2;
	public static final int FREE_TYPE = 3;
	public static final int TRADITION_TYPE = 4;
	public static final int TEST_TYPE = 5;

	public static final short LOCK = 1;		//parentControl
	public static final short UNLOCK = 0;	//parentControl
	public static final short PAY_ON = 1;	//payService
	public static final short PAY_OFF = 0;	//payService
	public static final short WELCOME_FIRST_TIME = 1;
	public static final short WELCOME_DISABLE = 0;

	public static final String WARNED = "1";	//已顯示WARN
	public static final String UNWARN = "0";	//未顯示WARN
	
	/** 親子鎖密碼：FOR 開房使用作<B>ADULT WARNING</B>使用*/
	public static final String PASSWORD_ADULT_WARNING = "";
	
	/** 電影權限 */
	public static final String TVRIGHT_TU = "TU";	//全部可看
	public static final String TVRIGHT_TX = "TX";	//全部不可看
	public static final String TVRIGHT_TM = "TM";	//限制級不可看
}
