package com.sidc.rcu.connection.rmi;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sidc.rcu.connector.bean.receiver.RCUReceiverInfo;
import com.sidc.rcu.connector.bean.receiver.RCUServiceReceiver;
import com.sidc.rcu.connector.rmi.intf.RCUReciverRemote;

public class RCURemoteClient {

	private List<Serializable> list;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		try {
			RCUServiceReceiver serviceReceive = new RCUServiceReceiver("SOS", 1);
			list.add(serviceReceive);
			RCUReceiverInfo receive = new RCUReceiverInfo(UUID.randomUUID().toString(), "608", "", list);
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 3202);
			RCUReciverRemote stub = (RCUReciverRemote) registry
					.lookup("com.sidc.rcu.connector.rmi.intf.RCUReciverRemote");
			stub.notice(receive);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
