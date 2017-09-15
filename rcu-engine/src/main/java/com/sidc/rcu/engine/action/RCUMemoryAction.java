/**
 * 
 */
package com.sidc.rcu.engine.action;

import com.sidc.rcu.engine.abs.AbstractChainOfBehavior;
import com.sidc.rcu.engine.behavior.RCUMemoryBehavior;
import com.sidc.rcu.engine.intf.RCUAction;
import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public class RCUMemoryAction implements RCUAction {

	public RCUMemoryAction() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.rcu.manager.intf.RCUAction#execute()
	 */
	public void execute() throws SiDCException {

		AbstractChainOfBehavior memory = new RCUMemoryBehavior();
		memory.setNextBehavior(null);
		memory.run();

	}

}
