/**
 * 
 */
package com.sidc.ra.logical.api.rcugroup;

import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuGroupEnity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupManager;
import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public class RcuGroupListProcess extends AbstractAPIProcess {

	/**
	 * 
	 */
	public RcuGroupListProcess() {
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

		List<RcuGroupEnity> roomsInfo = RcuGroupManager.getInstance().listRoomRCU();

		return roomsInfo;
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
