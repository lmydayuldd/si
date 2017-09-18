package com.sidc.tester.api.scenario;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sidc.rcu.connector.bean.command.AirConditionCommander;
import com.sidc.rcu.connector.bean.command.BulbCommander;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.bean.command.ServiceCommander;
import com.sidc.rcu.sdk.engine.RCUCommandClient;
import com.sidc.tester.api.RcuEngineRequestAPI;
import com.sidc.tester.api.url.Env;

public class RcuEngineRequestAPITester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		
		try {
//			AirConditionCommander hvac = new AirConditionCommander((byte) 0);
//			hvac.setPower(true);
//			hvac.setSpeed(2);
//			hvac.setFunction(1);

//			BulbCommander bulb = new BulbCommander(1);
			
			ServiceCommander service = new ServiceCommander(1);
			
			RCUCommander rcuCommander = new RCUCommander(UUID.randomUUID().toString(), "601", "MUR", service);
			
			new RCUCommandClient<Object>(Env.RCU_ENGINE, rcuCommander).execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
