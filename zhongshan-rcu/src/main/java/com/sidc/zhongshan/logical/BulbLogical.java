package com.sidc.zhongshan.logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sidc.configuration.common.key.CommonCatalogueRCUKey;
import com.sidc.dao.ra.response.DeviceCatalogue;
import com.sidc.dao.ra.response.DeviceEnity;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.raudp.bean.PositionBean;
import com.sidc.rcu.connector.bean.receiver.BulbReceiver;
import com.sidc.rcu.connector.common.DataKey;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class BulbLogical implements RCULogical {

	private String roomno;
	private Map<Integer, String> bulbMap;
	private List<Serializable> list = new ArrayList<Serializable>();

	public BulbLogical(String roomno, Map<Integer, String> bulbMap) {
		this.roomno = roomno;
		this.bulbMap = bulbMap;
	}

	public List<Serializable> execute() throws SiDCException {
		// TODO Auto-generated method stub
		Map<String, RoomRcuEnity> map = (Map<String, RoomRcuEnity>) DataCenter.getInstance()
				.get(DataKey.RCU_KEYCODE_MODULE);
		RoomRcuEnity rcuEnity = map.get(this.roomno);

		List<DeviceCatalogue> listCatalogue = rcuEnity.getCatalogues();
		for (DeviceCatalogue deviceCatalogue : listCatalogue) {
			List<DeviceEnity> listEnity = deviceCatalogue.getDevices();

			if (!deviceCatalogue.getCatalogue().equals(CommonCatalogueRCUKey.BULB)) {
				continue;
			}
			
			for (DeviceEnity deviceEnity : listEnity ) {
				PositionBean pos = (PositionBean) deviceEnity.getAppender();
				int status = 0;

				int[] arr = new int[] { 0, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
				if (Arrays.binarySearch(arr, pos.getAddress()) > -1) {
					status = Integer.parseInt(bulbMap.get(pos.getAddress()), 16);
				}

				arr = new int[] { 2, 3, 4, 5, 6, 7, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
				if (Arrays.binarySearch(arr, pos.getAddress()) > -1) {
					String circuit = "";
					String[] listCircuit = new String[] { "00", "00", "00", "00" };
					char[] address = bulbMap.get(pos.getAddress()).toCharArray();
					for (char c : address) {
						int turn10 = Integer.parseInt(String.valueOf(c), 16);
						circuit += Utils.getInstance().makesUpZero(Integer.toBinaryString(turn10), 4);
					}

					String string = "";
					for (int i = 0; i < circuit.length(); i++) {
						string += String.valueOf(circuit.toCharArray()[i]);
						if (i % 2 == 1) {
							int n = i / 2;
							listCircuit[n] = string;
							string = "";
						}
					}
					status = Integer.parseInt(listCircuit[pos.getCircuit()]);
				}
				list.add(new BulbReceiver(deviceEnity.getKeycode(), status));
			}
		}
		return list;
	}

}
