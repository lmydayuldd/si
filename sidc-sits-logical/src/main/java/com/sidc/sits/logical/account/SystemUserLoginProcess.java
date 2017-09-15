/**
 * 
 */
package com.sidc.sits.logical.account;

import org.apache.commons.lang3.StringUtils;

import com.derex.cm.stb.api.request.SystemUserLogin;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.AccountManager;
import com.sidc.utils.encrypt.AesEncrypt;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class SystemUserLoginProcess extends AbstractAPIProcess {

	private SystemUserLogin enity;

	public SystemUserLoginProcess(SystemUserLogin enity) {
		this.enity = enity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request=" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		AesEncrypt encrpyt = new AesEncrypt("sidc");

		final String token = AccountManager.getInstance().login(enity.getUser(), encrpyt.encrypt(enity.getPassword()),
				enity.getInfo());

//		return encrpyt.encrypt(token);
		return token;
	}

	@Override
	protected void check() throws SiDCException, Exception {

		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}

		if (StringUtils.isBlank(enity.getUser())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of User.");
		}

		if (StringUtils.isBlank(enity.getPassword())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of Password.");
		}
	}

}
