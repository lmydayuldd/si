package com.sidc.rcu.connector.udp.intf;

import com.sidc.rcu.connector.connector.RcuConnector;
import com.sidc.utils.exception.SiDCException;

public interface RCUPool {

	void put(String key, RcuConnector value) throws SiDCException, Exception;

	RcuConnector get(String key);

	void remove(String key) throws SiDCException;

	void clear();

	int size();

}
