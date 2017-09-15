package com.sidc.rcu.hmi.common;

public interface CommonDataKey {
	public final static String UDP_THEAD = "UDP_THEAD";

	public final static String RCU_UDP_SEND = "RCU_UDP_SEND";

	public final static String RCU_UDP_SERVER = "RCU_UDP_SERVER";

	public final static String RCU_UDP_REC = "RCU_UDP_REC";

	public final static String RCU_ROOM_MODULE = "RcuRoomModule";

	public final static String RCU_ROOM_STATUS = "RcuRoomStatus";// 心跳

	public final static String RCU_HVAC_INFO = "RcuHvacInfo";// 冷氣

	public final static String RCU_ROOM_INFO = "RcuRoomInfo";// 房間資訊

	public final static String RCU_CARD_INFO = "RcuCardInfo";// 卡片種類

	public final static String RCU_BULB_INFO = "RcuBulbInfo";// 燈泡種類

	public final static String RCU_SERVICE_INFO = "RcuCatalogueInfo";//

	public final static String WEBSOCKET_SETTING = "WebsocketSetting";// 設定檔
	
	public final static String WEBSOCKET_CLIENT = "WebsocketClient";//首頁 websocket client
	
	public final static String BLACKCORE_SETTING = "BlackcoreSetting";//設定檔
	
	public final static String WEBSOCKET_CLIENT_ROOM_CONTROL = "WebsocketClientRoomControl";//DEMO ROOM_CONTROL

}
