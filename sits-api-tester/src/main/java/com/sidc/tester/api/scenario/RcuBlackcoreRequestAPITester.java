package com.sidc.tester.api.scenario;

import static org.junit.Assert.*;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.sidc.dao.rcu.command.response.RcuModelCondition;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.rcu.connector.bean.command.AirConditionCommander;
import com.sidc.rcu.connector.bean.command.BulbCommander;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.bean.command.ServiceCommander;
import com.sidc.sdk.blackcore.rcu.command.AskCommandClient;
import com.sidc.sdk.blackcore.rcu.command.BulbCommandClient;
import com.sidc.sdk.blackcore.rcu.command.HvacCommandClient;
import com.sidc.sdk.blackcore.rcu.command.ServiceCommandClient;
import com.sidc.sdk.blackcore.rcu.mode.RcuModeClient;
import com.sidc.tester.api.url.Env;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.net.UDPConnection;

public class RcuBlackcoreRequestAPITester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		try {
			AirConditionCommander common = new AirConditionCommander((byte) 8);
			common.setPower(true);
			common.setFunction(1);
			common.setTemperature(25);
			common.setSpeed(0);
			common.setTimer(10);
			
//			BulbCommander common = new BulbCommander(1);
//			
//			ServiceCommander common = new ServiceCommander(1);
			
			RCUCommander commander = new RCUCommander(UUID.randomUUID().toString(), "608", "HVAC-ALL", common);
			new HvacCommandClient<Object>(Env.BLACKCORE_HOST, commander).execute();	

//			RCUCommander commander1 = new RCUCommander(UUID.randomUUID().toString(), "601", "ASK");
//			new AskCommandClient<>(Env.BLACKCORE_HOST, commander1).execute();
		} catch (SiDCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		try {
			RcuRoomMode request = new RcuRoomMode("608", "SLEEP");
			new RcuModeClient<Object>(Env.BLACKCORE_HOST, request).execute();
//			RcuRoomMode request1 = new RcuRoomMode("608", "WELCOME");
//			new RcuModeClient<Object>(Env.BLACKCORE_HOST, request1).execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
