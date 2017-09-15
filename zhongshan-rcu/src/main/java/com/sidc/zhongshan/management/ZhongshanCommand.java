package com.sidc.zhongshan.management;

import java.util.List;
import java.util.Map;

import com.sidc.configuration.common.key.CommonCatalogueRCUKey;
import com.sidc.dao.ra.response.DeviceCatalogue;
import com.sidc.dao.ra.response.DeviceEnity;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.raudp.bean.PositionBean;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.common.DataKey;
import com.sidc.rcu.connector.common.RCUKeycode;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.abs.AbstractCommand;
import com.sidc.zhongshan.handler.AskHandle;
import com.sidc.zhongshan.handler.BulbHandle;
import com.sidc.zhongshan.handler.HVACHandle;
import com.sidc.zhongshan.handler.ServiceHandle;

/**
 * 
 * @author Allen Huang
 *
 */
public class ZhongshanCommand extends AbstractCommand {

	public ZhongshanCommand(RCUCommander commander) {
		super(commander);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String handle() throws SiDCException {
		// TODO Auto-generated method stub		
		String data = "";
		if (getCommander().getKeycode().equals(RCUKeycode.ASK)) {
			data = "0xA1";
			data += new AskHandle(getCommander()).handle();
		} else {
			Map<String, RoomRcuEnity> map = (Map<String, RoomRcuEnity>) DataCenter.getInstance()
					.get(DataKey.RCU_KEYCODE_MODULE);
			RoomRcuEnity rcuEnity = map.get(getCommander().getRoomno());
			
			List<DeviceCatalogue> listCatalogue = (List<DeviceCatalogue>) rcuEnity.getCatalogues();
			for (DeviceCatalogue deviceCatalogue : listCatalogue) {
				List<DeviceEnity> listEnity = (List<DeviceEnity>) deviceCatalogue.getDevices();
				for (DeviceEnity deviceEnity : listEnity) {
					if (deviceEnity.getKeycode().equals(getCommander().getKeycode())) {
						data = "0x" + Integer.toHexString(deviceCatalogue.getCommand()).toUpperCase();
						switch (deviceCatalogue.getCommand()) {
						case 193:
							data += new HVACHandle(getCommander()).handle();
							break;
						case 194:
							PositionBean pos = (PositionBean) deviceEnity.getAppender();
							data += new BulbHandle(getCommander(), pos).handle();
							break;
						case 195:
							data += new ServiceHandle(getCommander()).handle();
							break;
						}
					}
				}
				if (deviceCatalogue.getCatalogue().equals(CommonCatalogueRCUKey.AIR_CONDITION)
						&& getCommander().getKeycode().equals("HVAC-ALL")) {
					data = "0x" + Integer.toHexString(deviceCatalogue.getCommand()).toUpperCase();
					data += new HVACHandle(getCommander()).handle();
				}
				if (deviceCatalogue.getCatalogue().equals(CommonCatalogueRCUKey.BULB)
						&& getCommander().getKeycode().equals("MASTER")) {
					data = "0x" + Integer.toHexString(deviceCatalogue.getCommand()).toUpperCase();
					for (int i = 0; i < 40; i++) {
						data += "0x00";
					}
				}
			}
		}
		return data;
	}
}
