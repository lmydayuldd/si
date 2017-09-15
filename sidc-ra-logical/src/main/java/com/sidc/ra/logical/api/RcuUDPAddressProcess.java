/**
 * 
 */
package com.sidc.ra.logical.api;

import com.sidc.blackcore.api.ra.response.RaUDPEnity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.ra.logical.common.CommonDataKey;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.SystemStatus;

/**
 * @author Nandin
 *
 */
public class RcuUDPAddressProcess extends AbstractAPIProcess {

	private final static int RA_UDP_SEND = 8025;
	private final static int RA_UDP_REC = 8026;

	/**
	 * 
	 */
	public RcuUDPAddressProcess() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#init()
	 */
	@Override
	protected void init() throws SiDCException, Exception {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#process()
	 */
	@Override
	protected Object process() throws SiDCException, Exception {

		Object ipLocalAddr = DataCenter.getInstance().get(CommonDataKey.RCU_MANAGER_ADRESS);

		if (ipLocalAddr == null) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "Local Address is not initial.");
		}

		return new RaUDPEnity(ipLocalAddr.toString(), RA_UDP_SEND, RA_UDP_REC);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#check()
	 */
	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

}
