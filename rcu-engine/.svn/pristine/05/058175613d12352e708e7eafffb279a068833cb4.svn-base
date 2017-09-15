/**
 * 
 */
package com.sidc.rcu.engine.abs;

import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public abstract class AbstractChainOfBehavior {

	private AbstractChainOfBehavior nextBehavior;

	public void run() throws SiDCException {

		process();
		
		if(this.nextBehavior != null){
			this.nextBehavior.run();
		}
		
	}

	protected abstract void process() throws SiDCException;

	public void setNextBehavior(AbstractChainOfBehavior nextBehavior) {
		this.nextBehavior = nextBehavior;
	}
	
	
}
