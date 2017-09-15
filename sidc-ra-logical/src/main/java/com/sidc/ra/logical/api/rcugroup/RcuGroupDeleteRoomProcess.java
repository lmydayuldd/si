/**
 * 
 */
package com.sidc.ra.logical.api.rcugroup;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupDeleteRoomEnity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class RcuGroupDeleteRoomProcess extends AbstractAPIProcess {

	private RcuGroupDeleteRoomEnity enity;

	/**
	 * 
	 */
	public RcuGroupDeleteRoomProcess(RcuGroupDeleteRoomEnity enity) {
		this.enity = enity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.common.framework.abs.AbstractAPIProcess#init()
	 */
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.common.framework.abs.AbstractAPIProcess#process()
	 */
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		RcuGroupManager.getInstance().delete(this.enity.getRoomno());

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.common.framework.abs.AbstractAPIProcess#check()
	 */
	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}

		if (StringUtils.isBlank(this.enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Room NO is empty");
		}

	}

}
