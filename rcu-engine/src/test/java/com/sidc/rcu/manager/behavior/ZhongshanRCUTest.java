//package com.sidc.rcu.manager.behavior;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.net.DatagramPacket;
//
//import org.apache.commons.codec.CharEncoding;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.sidc.ra.udp.connection.pool.RCUSenderPool;
//import com.sidc.rcu.connector.bean.command.AirConditionCommander;
//import com.sidc.rcu.connector.bean.command.BulbCommander;
//import com.sidc.rcu.connector.bean.command.RCUCommander;
//import com.sidc.rcu.connector.bean.command.ServiceCommander;
//import com.sidc.rcu.manager.initial.SystemInitial;
//import com.sidc.utils.exception.SiDCException;
//
//public class ZhongshanRCUTest {
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		new SystemInitial().initial();
//	}
//
//	@Test
//	public void test() {
////		AirConditionCommander airConditionCommander = new AirConditionCommander((byte) 0);
////		airConditionCommander.setPower(true);
////		airConditionCommander.setFunction(2);
////		airConditionCommander.setTemperature(20);
////		airConditionCommander.setSpeed(0);
////		RCUCommander command = new RCUCommander("608", "HAVC", airConditionCommander);
//		BulbCommander bulbCommander	= new BulbCommander(0);
//		RCUCommander command = new RCUCommander("608", "NIGHT", bulbCommander);
////		ServiceCommander serviceCommander = new ServiceCommander(1);
////		RCUCommander command = new RCUCommander("608", "DND", serviceCommander);
////		RCUCommander command = new RCUCommander("608", "ASK");
//		
//		try {
//			RCUSenderPool.getInstance().get("608").send(command);
//			while (true) {
//				RCUSenderPool.getInstance().get("608").receive();
//			}
//		} catch (SiDCException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
