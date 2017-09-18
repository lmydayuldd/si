package com.sidc.tester.api.scenario;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.derex.cm.stb.api.display.AttributeType;
import com.derex.cm.stb.api.display.BootMode;
import com.derex.cm.stb.api.display.LogFlags;
import com.derex.cm.stb.api.display.Resolution;
import com.derex.cm.stb.api.display.ShowChannelOsd;
import com.derex.cm.stb.api.display.Volcontrol;
import com.derex.cm.stb.api.request.RoomListStbIPRequest;
import com.derex.cm.stb.api.request.StbAskVersionRequest;
import com.derex.cm.stb.api.request.StbBootModeRequest;
import com.derex.cm.stb.api.request.StbCheckoutRequest;
import com.derex.cm.stb.api.request.StbMessageRequest;
import com.derex.cm.stb.api.request.StbOpenTvByChannelNoRequest;
import com.derex.cm.stb.api.request.StbPropertySettingRequest;
import com.derex.cm.stb.api.request.StbRebootRequest;
import com.derex.cm.stb.api.request.StbRedirectPageRequest;
import com.derex.cm.stb.api.request.StbResetRoomNoRequest;
import com.derex.cm.stb.api.request.StbSystemIniTemplateRequest;
import com.derex.cm.stb.api.request.StbUpgradeFirmwareRequest;
import com.derex.cm.stb.api.request.StbWakeupRequest;
import com.derex.cm.stb.api.request.StbWiFiOffRequest;
import com.derex.cm.stb.api.request.StbWiFiOnRequest;
import com.sidc.tester.api.STBCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class STBCommanderTester {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void testWifiRestartShouldWork() {
	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {

	    StbWiFiOffRequest stbWiFiOffRequest = new StbWiFiOffRequest(stbs);
	    STBCommander.getInstance().wifiOff(stbWiFiOffRequest);

	    Thread.sleep(3000);

	    StbWiFiOnRequest stbWiFiOnRequest = new StbWiFiOnRequest(stbs);
	    STBCommander.getInstance().wifiOn(stbWiFiOnRequest);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testWakeupShouldWork() {
	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {

	    StbWakeupRequest request = new StbWakeupRequest(stbs, "01");
	    STBCommander.getInstance().wakeup(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    /*
     * �ϥιq���\��A�ùw�]�q���W�D
     * 
     */
    @Test
    public void testOpenTvByChannelNo() {
	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbOpenTvByChannelNoRequest request = new StbOpenTvByChannelNoRequest(stbs, "01");
	    STBCommander.getInstance().opentvbychannelno(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    /*
     * �߰� STB ������ �����|�^�ǩ� Response
     * 
     */
    @Test
    public void testAskVersionNo() {
	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbAskVersionRequest request = new StbAskVersionRequest(stbs);
	    STBCommander.getInstance().askversionno(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    /*
     * Rest STB �w�� IP
     * 
     */
    @Test
    public void testRestRoom() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbResetRoomNoRequest request = new StbResetRoomNoRequest(stbs);
	    STBCommander.getInstance().restroom(request);
	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testMessage() {

	try {

	    StbMessageRequest request = new StbMessageRequest("508", "test   test");
	    STBCommander.getInstance().message(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testMessageOff() {

	try {

	    RoomListStbIPRequest request = new RoomListStbIPRequest("508");
	    STBCommander.getInstance().messageoff(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testUpgradeFirmware() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {

	    StbUpgradeFirmwareRequest request = new StbUpgradeFirmwareRequest(stbs, "MCXX.3111.462.1.24_RC7_20160624");
	    STBCommander.getInstance().upgradefirmware(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testSystemIniTemplate() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbSystemIniTemplateRequest request = new StbSystemIniTemplateRequest(stbs, Resolution.R720p, 95, 30,
		    ShowChannelOsd.ON, LogFlags.DEFAULT, Volcontrol.TV);
	    STBCommander.getInstance().systeminitemplate(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testPropertySetting() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {

	    StbPropertySettingRequest request = new StbPropertySettingRequest(stbs, AttributeType.DISPLAYSCALESIZE,
		    "97");
	    STBCommander.getInstance().propertysetting(request);

	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testBootMode() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbBootModeRequest request = new StbBootModeRequest(stbs, BootMode.TV, "03");
	    STBCommander.getInstance().bootmode(request);
	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testReboot() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbRebootRequest request = new StbRebootRequest(stbs);
	    STBCommander.getInstance().reboot(request);
	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testRedirect() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbRedirectPageRequest request = new StbRedirectPageRequest(stbs, "front/menu.htm");
	    STBCommander.getInstance().redirect(request);
	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }

    @Test
    public void testCheckout() {

	List<String> stbs = new ArrayList<String>();
	stbs.add("10.60.5.8");

	try {
	    StbCheckoutRequest request = new StbCheckoutRequest(stbs);
	    STBCommander.getInstance().checkout(request);
	} catch (SiDCException e) {
	    assertEquals(APIStatus.SUCCESS, e.getErrorCode());
	} catch (Exception e) {
	    assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);
	}
    }
}
