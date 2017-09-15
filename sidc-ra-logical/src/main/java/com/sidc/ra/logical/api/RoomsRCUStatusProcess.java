/**
 * 
 */
package com.sidc.ra.logical.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import com.sidc.blackcore.api.ra.response.RoomRCUStatusEnity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public class RoomsRCUStatusProcess extends AbstractAPIProcess {

	/**
	 * 
	 */
	public RoomsRCUStatusProcess() {
		// TODO Auto-generated constructor stub
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
	protected RoomRCUStatusEnity process() throws SiDCException, Exception {

		Ignite ignite = Ignition.ignite();
		IgniteCache<String, RoomRcuEnity> roomRCUStatusCache = ignite.getOrCreateCache("RoomRCUStatus");

		Iterator<Entry<String, RoomRcuEnity>> it = roomRCUStatusCache.iterator();

		List<RoomRcuEnity> result = new ArrayList<RoomRcuEnity>();

		while (it.hasNext()) {
			Entry<String, RoomRcuEnity> entry = it.next();
			result.add(entry.getValue());
		}

		return new RoomRCUStatusEnity(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.common.framework.abs.AbstractAPIProcess#check()
	 */
	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

}
