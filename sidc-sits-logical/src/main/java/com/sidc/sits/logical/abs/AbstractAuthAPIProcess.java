/**
 * 
 */
package com.sidc.sits.logical.abs;

import com.sidc.utils.exception.SiDCException;

public abstract class AbstractAuthAPIProcess extends TokenAuthorization {

	public AbstractAuthAPIProcess(final String token) {
		super(token);
	}

	// mobile user
	public AbstractAuthAPIProcess(final String publicKey, final String privateKey, final String roomNo) {
		super(publicKey, privateKey, roomNo);
	}

	// staff
	public final Object executeWithStaffToken() throws SiDCException, Exception {

		init();

		check();

		super.authorize();

		Object obj = process();

		return obj;

	}

	// 驗證 token 跟 Api權限
	public final Object executeByAuthToken(final String url) throws SiDCException, Exception {

		init();

		check();

		super.authorizeByFuncitonList(url);

		Object obj = process();

		return obj;

	}

	// mobile user
	public final Object executeWithMobileToken() throws SiDCException, Exception {

		init();

		check();

		super.MobileAuthorize();

		Object obj = process();

		return obj;

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
