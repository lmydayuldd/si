package com.sidc.rcu.connector.connector;

import java.io.IOException;
import java.net.SocketException;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;

/**
 * 
 * @author Allen Huang
 *
 */
public interface RcuConnector {

	public boolean isConnection() throws SiDCException;
	
	public void init() throws SiDCException, Exception;
	
	public void send(RCUCommander command) throws SiDCException;
	
	public Object receive() throws SiDCException, SocketException, IOException, InterruptedException;
	
	public void close() throws SiDCException;
	
	public boolean testConnection();
}
