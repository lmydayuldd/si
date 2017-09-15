/**
 * 
 */
package com.sidc.ra.logical.api.rcu;

import java.util.List;

import com.sidc.blackcore.api.ra.response.RoomInfoEnity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RoomRCUManager;
import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public class RoomRcuInfoProcess extends AbstractAPIProcess {

	/**
	 * 
	 */
	public RoomRcuInfoProcess() {
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

		List<RoomInfoEnity> roomsInfo = RoomRCUManager.getInstance().listRoomRcuInfo();

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
