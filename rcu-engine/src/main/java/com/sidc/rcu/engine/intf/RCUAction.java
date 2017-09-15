/**
 * 
 */
package com.sidc.rcu.engine.intf;

import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public interface RCUAction {

	public void execute() throws SiDCException;
	
}
