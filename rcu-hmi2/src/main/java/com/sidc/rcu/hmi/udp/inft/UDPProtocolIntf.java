package com.sidc.rcu.hmi.udp.inft;

import com.sidc.utils.exception.SiDCException;

public interface UDPProtocolIntf {

	public void execute() throws SiDCException;
	
	public void close() ;
}

