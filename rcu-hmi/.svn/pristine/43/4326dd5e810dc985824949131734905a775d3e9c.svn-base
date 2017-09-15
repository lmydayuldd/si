//package com.sidc.ra.server.udp.receiver;
//
//import java.util.Date;
//import java.util.HashMap;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sidc.ra.server.bean.RcuHvacInfoBean;
//import com.sidc.ra.server.bean.RcuServiceBean;
//import com.sidc.ra.server.common.CommonDataKey;
//import com.sidc.ra.server.common.DataCenter;
//import com.sidc.ra.server.framework.abs.AbstractUDPReceiver;
//import com.sidc.utils.exception.SiDCException;
//import com.sidc.utils.log.LogAction;
//import com.sidc.utils.status.SystemStatus;
//
//public class RcuHvacReceiver extends AbstractUDPReceiver {
//	private final static Logger logger = LoggerFactory.getLogger(RcuHvacReceiver.class);
//	private final String roomNo;
//	private final RcuServiceBean entity;
//
//	public RcuHvacReceiver(String roomNo, RcuServiceBean entity) {
//		this.roomNo = roomNo;
//		this.entity = entity;
//	}
//
//	@Override
//	protected void init() throws SiDCException, Exception {
//		// TODO Auto-generated method stub
//		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
//		LogAction.getInstance().debug("Receiver Entity = " + entity + "room no = " + roomNo);
//	}
//
//	@Override
//	protected Object process() throws SiDCException, Exception {
//		// TODO Auto-generated method stub
//		HashMap<String, RcuHvacInfoBean> hvacMap = (HashMap<String, RcuHvacInfoBean>) DataCenter.getInstance()
//				.get(CommonDataKey.RCU_HVAC_INFO);
//
//		LogAction.getInstance().debug("Step 1/4 get map from DataCenter success.");
//
//		if (hvacMap == null) {
//			hvacMap = new HashMap<String, RcuHvacInfoBean>();
//			LogAction.getInstance().debug("Step 1(1)/4 map is null.");
//		}
//
//		LogAction.getInstance().debug("Step 2/4 check map(null or empty) success.");
//
//		hvacMap.put(roomNo,
//				new RcuHvacInfoBean(roomNo, entity.getStatus(), entity.getMode(), entity.getTemperature(),
//						entity.getSpeed(), entity.getActualTemp(), entity.getIsAirConditioning(), entity.getPosition(),
//						null, new Date()));
//
//		LogAction.getInstance().debug("Step 3/4 put map new entity success.");
//
//		DataCenter.getInstance().put(CommonDataKey.RCU_HVAC_INFO, hvacMap);
//
//		LogAction.getInstance().debug("Step 4/4 put map to DataCenter success.");
//		return null;
//	}
//
//	@Override
//	protected void check() throws SiDCException, Exception {
//		// TODO Auto-generated method stub
//		if (StringUtils.isBlank(roomNo)) {
//			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "Receiver roomNo is null.");
//		}
//		if (entity == null) {
//			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "Receiver entity is null.");
//		}
//	}
//}
