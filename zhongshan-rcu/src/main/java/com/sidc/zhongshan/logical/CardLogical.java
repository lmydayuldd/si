package com.sidc.zhongshan.logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sidc.rcu.connector.bean.receiver.CardReceiver;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.intf.CardKeyCode;

/**
 * 
 * @author Allen Huang
 *
 */
public class CardLogical implements RCULogical {

	private Map<Integer, String> map;
	private List<Serializable> list = new ArrayList<Serializable>();
	public CardLogical(Map<Integer, String> map) {
		this.map = map;
	}
	
	public List<Serializable> execute() throws SiDCException {
		
		int status = Integer.parseInt(map.get(0));
		int authorization = Integer.parseInt(map.get(1));
		int i = Integer.parseInt(map.get(2), 16);
		String cardType = CardKeyCode.values()[i].toString();
		
		list.add(new CardReceiver(status, authorization, cardType));
		
		return list;
	}
}
