package com.sidc.zhongshan.logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.connector.bean.receiver.HeartBeatReceiver;
import com.sidc.utils.exception.SiDCException;

/**
 * 
 * @author Allen Huang
 *
 */
public class HeartBeatLogical implements RCULogical {

	private List<Serializable> list = new ArrayList<Serializable>();
	public HeartBeatLogical() {
		
	}
	
	public List<Serializable> execute() throws SiDCException {
		// TODO Auto-generated method stub
		list.add(new HeartBeatReceiver(true));
		return list;
	}

}
