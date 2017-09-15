package com.sidc.zhongshan.abs;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;

/**
 * 
 * @author Allen Huang
 *
 */
public abstract class AbstractCommand {

	private RCUCommander commander;

	private AbstractCommand next;
	public AbstractCommand (RCUCommander commander) {
		this.commander = commander;
	}

	public void setNext(AbstractCommand next) {
		this.next = next;
	}
	
	public void process() throws SiDCException {
		handle();
		
		doNext();
	}
	
	protected abstract String handle() throws SiDCException;

	private void doNext() throws SiDCException {
		if (next != null) {
			next.handle();
		}
	}
	
	public RCUCommander getCommander() {
		return commander;
	}
}
