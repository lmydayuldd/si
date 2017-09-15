package com.sidc.zhongshan.logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sidc.rcu.connector.bean.receiver.RCUServiceReceiver;
import com.sidc.rcu.connector.common.ServiceName;
import com.sidc.utils.exception.SiDCException;

/**
 * 
 * @author Allen Huang
 *
 */
public class ServiceLogical implements RCULogical {

	private Map<Integer, String> map;
	private List<Serializable> list = new ArrayList<Serializable>();
	public ServiceLogical(Map<Integer, String> map) {
		this.map = map;
	}
	
	public List<Serializable> execute() throws SiDCException {
		// TODO Auto-generated method stub
		
		//SOS
		if (Integer.parseInt(map.get(0)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[0].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[0].name(), 0));
		}
		
		//MUR
		if (Integer.parseInt(map.get(1)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[1].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[1].name(), 0));
		}
		
		//DND
		if (Integer.parseInt(map.get(2)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[2].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[2].name(), 0));
		}
		
		//BUTLER
		if (Integer.parseInt(map.get(3)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[3].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[3].name(), 0));
		}
		
		//DOORLOCK
		if (Integer.parseInt(map.get(4)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[4].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[4].name(), 0));
		}
		
		//BLIND
		if (Integer.parseInt(map.get(5)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[5].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[5].name(), 0));
		}
		
		//VENTILATOR
		if (Integer.parseInt(map.get(6)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[6].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[6].name(), 0));
		}
		
		//CHECKOUT
		if (Integer.parseInt(map.get(7)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[7].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[7].name(), 0));
		}
		
		//DOORTIMEOUT
		if (Integer.parseInt(map.get(8)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[8].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[8].name(), 0));
		}

		//LAUNDRY
		if (Integer.parseInt(map.get(9)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[9].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[9].name(), 0));
		}
		
		//PICKUP
		if (Integer.parseInt(map.get(10)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[10].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[10].name(), 0));
		}
		
		//ROOMSERVICE
		if (Integer.parseInt(map.get(11)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[11].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[11].name(), 0));
		}
		
		//SAFE
		if (Integer.parseInt(map.get(12)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[12].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[12].name(), 0));
		}
		
		//WAIT
		if (Integer.parseInt(map.get(13)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[13].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[13].name(), 0));
		}
		
		//DOORSENSOR
		if (Integer.parseInt(map.get(14)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[14].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[14].name(), 0));
		}
		
		//PIR
		if (Integer.parseInt(map.get(15)) == 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[15].name(), 1));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[15].name(), 0));
		}
		
		//PSI
		if (Integer.parseInt(map.get(16)) >= 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[16].name(), Integer.parseInt(map.get(16))));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[16].name(), 0));
		}
		
		//HUMIDITY
		if (Integer.parseInt(map.get(17)) >= 1) {
			list.add(new RCUServiceReceiver(ServiceName.values()[17].name(), Integer.parseInt(map.get(17))));
		} else {
			list.add(new RCUServiceReceiver(ServiceName.values()[17].name(), 0));
		}
		
		return list;
	}

	
}
