/**
 * 
 */
package com.sidc.zhongshan.management;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.configuration.common.key.CommonCatalogueRCUKey;
import com.sidc.rcu.connector.bean.receiver.RCUReceiverInfo;
import com.sidc.rcu.connector.rmi.intf.RCUReciverRemote;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.RcuReceiverLog;
import com.sidc.zhongshan.intf.RcuConfig;
import com.sidc.zhongshan.intf.Utils;
import com.sidc.zhongshan.logical.AirConditionLogical;
import com.sidc.zhongshan.logical.BulbLogical;
import com.sidc.zhongshan.logical.CardLogical;
import com.sidc.zhongshan.logical.HeartBeatLogical;
import com.sidc.zhongshan.logical.ServiceLogical;

/**
 * @author Nandin
 *
 */
public class RCUReceiverNotice {

	private final static Logger logger = LoggerFactory.getLogger(RCUReceiverNotice.class);

	private DatagramPacket packet;
	private RCUReciverRemote stub;

	/**
	 * 
	 */
	public RCUReceiverNotice(DatagramPacket packet, RCUReciverRemote stub) {
		this.stub = stub;
		this.packet = packet;
	}

	public void process() {

		boolean writeLog = false;
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			final byte[] data = packet.getData();
			final InetAddress remoteAddress = packet.getAddress();

			if (data.length < 50)
				return;

			String receiver = "";
			String keycode = "";
			for (int i = 0; i < 50; i++) {
				int hex = (int) data[i] & 0xFF;
				String hexString = Utils.getInstance().makesUpZero(Integer.toHexString(hex).toUpperCase(), 2);
				if (i == 5)
					keycode = hexString;
				if (i >= 6)
					map.put(i - 6, hexString);
				receiver += "0x" + hexString;
			}

			writeLog = (keycode.equals(RcuConfig.HEARTBEAT)) ? false : true;
			RCUReceiverInfo rcuReceive = null;
			final String uuid = UUID.randomUUID().toString().replace("-", "");
			final String roomNo = Utils.getInstance().ipToRoomNo(remoteAddress);

			if (writeLog) {
				RcuReceiverLog.getInstance().initial(logger, uuid);
				if (!StringUtils.isBlank(remoteAddress.getHostAddress())) {
					RcuReceiverLog.getInstance().setRoomIP(remoteAddress.getHostAddress());
				}

				RcuReceiverLog.getInstance().debug("Sources：" + receiver);
				RcuReceiverLog.getInstance().setRoomNo(roomNo);
			}
			rcuReceive = receiverInfo(keycode, uuid, roomNo, map);

			this.stub.notice(rcuReceive);

		} catch (Exception e) {
			if (writeLog) {
				RcuReceiverLog.getInstance().error(e.getMessage(), e);
			}
		} finally {
			if (writeLog) {
				RcuReceiverLog.getInstance().writeRecord();
			}
		}
	}

	private RCUReceiverInfo receiverInfo(final String keycode, final String uuid, final String roomno,
			final Map<Integer, String> map) throws SiDCException {

		RCUReceiverInfo rcuReceive = null;
		switch (keycode) {
		case RcuConfig.SERVICE:
			rcuReceive = new RCUReceiverInfo(uuid, roomno, CommonCatalogueRCUKey.SERVICE,
					new ServiceLogical(map).execute());
			RcuReceiverLog.getInstance().setCatalogue(CommonCatalogueRCUKey.SERVICE);
			break;
		case RcuConfig.AIRCONDITION:
			rcuReceive = new RCUReceiverInfo(uuid, roomno, CommonCatalogueRCUKey.AIR_CONDITION,
					new AirConditionLogical(map).execute());
			RcuReceiverLog.getInstance().setCatalogue(CommonCatalogueRCUKey.AIR_CONDITION);
			break;
		case RcuConfig.BULB:
			rcuReceive = new RCUReceiverInfo(uuid, roomno, CommonCatalogueRCUKey.BULB,
					new BulbLogical(roomno, map).execute());
			RcuReceiverLog.getInstance().setCatalogue(CommonCatalogueRCUKey.BULB);
			break;
		case RcuConfig.CARD:
			rcuReceive = new RCUReceiverInfo(uuid, roomno, CommonCatalogueRCUKey.CARD, new CardLogical(map).execute());
			RcuReceiverLog.getInstance().setCatalogue(CommonCatalogueRCUKey.CARD);
			break;
		default:
			rcuReceive = new RCUReceiverInfo(uuid, roomno, CommonCatalogueRCUKey.HEARTBEAT,
					new HeartBeatLogical().execute());
			break;
		}

		return rcuReceive;
	}
}
