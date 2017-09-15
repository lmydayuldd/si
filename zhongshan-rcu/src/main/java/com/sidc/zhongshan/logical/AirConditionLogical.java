package com.sidc.zhongshan.logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sidc.rcu.connector.bean.receiver.AirConditionReceiver;
import com.sidc.utils.exception.SiDCException;

/**
 * 
 * @author Allen Huang
 *
 */
public class AirConditionLogical implements RCULogical {

	private Map<Integer, String> map;
	private List<Serializable> list = new ArrayList<Serializable>();
	public AirConditionLogical(Map<Integer, String> map) {
		this.map = map;
	}
	
	public List<Serializable> execute() throws SiDCException {
		// TODO Auto-generated method stub
		
		int status = map.containsKey(0) ? Integer.parseInt(map.get(0), 16) : -1;
		int mode = map.containsKey(1) ? Integer.parseInt(map.get(1), 16) : -1;
		int temperature = map.containsKey(2) ? Integer.parseInt(map.get(2), 16) : -1;
		int speed = map.containsKey(3) ? Integer.parseInt(map.get(3), 16) : -1;
		int actualTemperature = map.containsKey(4) ? Integer.parseInt(map.get(4), 16) : -1;
		int isAirConditioning = map.containsKey(5) ? Integer.parseInt(map.get(5), 16) : -1;
		int position = map.containsKey(6) ? Integer.parseInt(map.get(6), 16) : -1;

		list.add(new AirConditionReceiver(status, mode, temperature, speed, actualTemperature, isAirConditioning, position));
		
		return list;
	}

}
