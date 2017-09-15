/**
 * 
 */
package com.sidc.common.framework.abs;

import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin 
 *
 */
public abstract class AbstractAPIProcess {

	public AbstractAPIProcess() {
		super();
	}

	public final Object execute() throws SiDCException, Exception {

		init();

		check();

		Object obj = process();

		return obj;

	}

	protected abstract void init() throws SiDCException, Exception;

	protected abstract Object process() throws SiDCException, Exception;

	protected abstract void check() throws SiDCException, Exception;

}
