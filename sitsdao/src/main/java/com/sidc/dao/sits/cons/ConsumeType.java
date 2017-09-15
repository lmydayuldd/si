package com.sidc.dao.sits.cons;

/**
 * 
 * @author Allen Huang
 *
 */
public interface ConsumeType {

	  public static final String UNPOST = "UNPOST";
	  public static final String POSTED = "POSTED";
	  
	  /**無PMS系統時，開啟系統服務(相當於check-in)所需的charge*/
	  public static final short CHECK_IN = 0;
	  /**TV service*/
	  public static final short TV = 1;
	  /**Pay Per View movie service(charge by movie)*/
	  public static final short MOVIE = 2;
	  /**Pay Per Package movie service(charge by package)*/
	  public static final short SCHEDULE_MOVIE = 3;
	  /**MSN service*/
	  public static final short IM = 4;
	  /**Game on TV service*/
	  public static final short GAME = 5;
	  /**Internet Phone service*/
	  public static final short IP_PHONE = 6;
	  /**Minibar Report*/
	  public static final short MINIBAR = 7;
	  /**Internet on TV service*/
	  public static final short INTERNET = 8;
	  /**Broadband service*/
	  public static final short IP3 = 9;
	  /**Pay For All movie service(charge for all package)*/
	  public static final short FULL_MOVIE = 10;
	  /**Pay Per Day movie service(charge by movie)*/
	  public static final short PPD_MOVIE = 11;
	  /**RSS News service*/
	  public static final short NEWS = 12;
	  /**Adult movie service*/
	  public static final short ADULT_MOVIE = 13;
	  /**Adult movie package service*/
	  public static final short ADULT_MOVIE_PACKAGE = 14;
	  /**Shopping service*/
	  public static final short SHOP = 15;
	  /**Room service*/
	  public static final short ROOMSERVICE = 16;
	  
	  
	  public static final String SHOP_CHARGE = "SHOP_CHARGE";
}
