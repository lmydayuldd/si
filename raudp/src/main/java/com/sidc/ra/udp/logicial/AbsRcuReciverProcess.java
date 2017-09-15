/**
 * 
 */
package com.sidc.ra.udp.logicial;

import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public abstract class AbsRcuReciverProcess {

	public void execute() throws SiDCException{
		
		init();
		
		check();
		
		process();
		
	}
	
	protected abstract void init() throws SiDCException;
	
	protected abstract void check() throws SiDCException;
	
	protected abstract void process() throws SiDCException;
}