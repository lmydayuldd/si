package com.sidc.sits.logical.parameter;

public interface PageList {

	public final static String MOVIE_PAGE = "/sits/front/movieIndex.htm?bySocket";
	public final static String TV_PAGE = "/sits/front/tv.htm?bySocket";
	public final static String MENU_PAGE = "/sits/front/menu.htm?bySocket";
	public final static String WELCOME_PAGE = "/sits/front/index.htm?bySocket";
	public final static String MSG_LIST_PAGE = "http://localhost/sits/stb/api/message.json";
	public final static String EMERGENCY_PAGE = "/emergency/emergency.htm?bySocket";
	public final static String M_MOVIE_PAGE = "/sits/front/movieIndex.htm?bySocket_mobile";
	public final static String M_RADIO_PAGE = "/sits/front/radio.htm?openCategoryPopup=N&bySocket_mobile";
	public final static String M_SHOP_PAGE = "/sits/jsp/front/shopping/shopping.jsp?bySocket_mobile";
	public final static String M_MEAL_PAGE = "/sits/jsp/front/shopping/meal.jsp?bySocket_mobile";
	public final static String MOBILE_CONTROL_PAGE = "/sits/front/mobileAppRemote.htm?bySocket";

	// radio
	public final static String RADIO_CLOSE = "/radio/close?ip=";

	// Bill
	public final static String BILL_REVIEW = "/api/billreview.json";

	// STB
	public final static String STB_WELCOME_PAGE = "/stb/api/redirect.json";
	public final static String STB_REBOOT = "/stb/api/stbreboot.json";
	public final static String STB_CHECK_OUT = "/stb/api/checkout.json";
	public final static String STB_TV_CHANNEL_CHANGE = "/stb/api/opentvbychannelno.json";
	public final static String STB_MOVIE_PLAY = "/stb/api/opentvbymovie.json";
	public final static String STB_REMOTE_CONTROL = "/stb/api/remotecontrol.json";

	// photo upload
	public final static String UPLOAD_PHOTO = "/api/photoupload.json";
	// photo delete
	public final static String DELETE_PHOTO = "/api/photodelete.json";

	// print data
	public final static String PRINT_DATA = "/api/printdata.json";

	// hotel info
	public final static String HOTEL_INFO = "/api/hotelinfo.json";

}
