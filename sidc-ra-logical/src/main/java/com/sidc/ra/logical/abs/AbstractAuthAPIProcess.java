/**
 * 
 */
package com.sidc.ra.logical.abs;

import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public abstract class AbstractAuthAPIProcess extends TokenAuthorization {

	public AbstractAuthAPIProcess(final String token, final String roomNo, final String address) {
		super(token, roomNo, address);
	}

	public AbstractAuthAPIProcess(final String token, final String privateKey, final String roomNo,
			final String address) {
		super(token, privateKey, roomNo, address);
	}

	public AbstractAuthAPIProcess(final String roomNo, final String address) {
		super(roomNo, address);
	}

	public final Object executeWithMobileToken() throws SiDCException, Exception {

		init();

		check();

		super.MobileAuthorize();

		Object obj = process();

		return obj;

	}

	public final Object executeWithDomain() throws SiDCException, Exception {

		init();

		check();

		super.domainAuthorize();

		Object obj = process();

		return obj;

	}

	public final Object executeWithTesters() throws SiDCException, Exception {

		init();

		check();

		Object obj = process();

		return obj;

	}

	protected abstract void init() throws SiDCException, Exception;

	protected abstract Object process() throws SiDCException, Exception;

	protected abstract void check() throws SiDCException, Exception;

}
