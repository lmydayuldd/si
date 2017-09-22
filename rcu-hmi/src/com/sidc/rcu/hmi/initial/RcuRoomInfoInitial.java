package com.sidc.rcu.hmi.initial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.conf.PmsCommonKey;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.room.bean.RoomStatusListBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.SystemStatus;

/**
 * @author Joe
 * 
 * @see 取得check in,out room info
 */
public class RcuRoomInfoInitial extends AbstractAPIProcess {
	private final static Logger logger = LoggerFactory.getLogger(RcuRoomInfoInitial.class);
	private RoomStatusListBean entity;

	@SuppressWarnings("unchecked")
	public RcuRoomInfoInitial(final String apiRequest) throws SiDCException {
		entity = (RoomStatusListBean) APIParser.getInstance().parses(apiRequest, RoomStatusListBean.class);
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
		LogAction.getInstance().debug("entity:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		DataCenter.getInstance().put(PmsCommonKey.CHECKIN, entity.getCheckinrooms());
		DataCenter.getInstance().put(PmsCommonKey.CHECKOUT, entity.getCheckoutrooms());

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "RoomStatusListBean entity is null.");
		}
	}

}
