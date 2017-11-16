package com.sidc.tester.api.scenario;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.derex.cm.stb.api.request.RoomListStbIPRequest;
import com.derex.cm.stb.api.response.RoomListStbIPResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.request.RoomModuleRequest;
import com.sidc.blackcore.api.sits.room.response.RoomNoListResponse;
import com.sidc.raudp.bean.RoomModuleBean;
import com.sidc.tester.api.BlackcoreRequestAPI;
import com.sidc.tester.api.SiTSRequestAPI;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class SiTSRequsetAPITester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testRoomListStbStatusShouldWork() {

		try {

			RoomListStbIPRequest request = new RoomListStbIPRequest("508");
			RoomListStbIPResponse response = SiTSRequestAPI.getInstance().listRoomStbStatu(request);

			assertEquals(false, response.getStb().isEmpty());
		} catch (SiDCException e) {
			assertEquals(APIStatus.SUCCESS, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);

		}
	}

	@Test
	public void testRoomNoList() {
		try {

			RoomNoListResponse response = BlackcoreRequestAPI.getInstance().listRoomNo();

			System.out.println(response);

		} catch (SiDCException e) {
			assertEquals(APIStatus.SUCCESS, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);

		}
	}

	@Test
	public void testInitZhongshanModule() {
		try {

			File file = new File("D:/workspace/sidc-blackcore/rcu-engine/rcu-manager/zhongshan/room-module-tester.json");
			String json = FileUtils.readFileToString(file, CharEncoding.UTF_8);

			List<RoomModuleBean> roomModuleBeans = new Gson().fromJson(json, new TypeToken<List<RoomModuleBean>>() {
			}.getType());

			RoomModuleRequest request = new RoomModuleRequest(roomModuleBeans);
			BlackcoreRequestAPI.getInstance().initZhongshanModule(request);

		} catch (SiDCException e) {
			assertEquals(APIStatus.SUCCESS, e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(APIStatus.SUCCESS, APIStatus.GENERAL_ERROR);

		}
	}

	@Test
	public void testListRoomRCU() {
		try {
			System.out.println(BlackcoreRequestAPI.getInstance().listRoomRCU());
		} catch (SiDCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListRoomInfo() {
		try {
			System.out.println(BlackcoreRequestAPI.getInstance().listRoomInfo());
		} catch (SiDCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
