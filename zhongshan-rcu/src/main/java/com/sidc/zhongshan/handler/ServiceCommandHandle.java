package com.sidc.zhongshan.handler;

import com.sidc.rcu.connector.common.ServiceName;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.abs.AbstractService;
import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class ServiceCommandHandle extends AbstractService {

	public ServiceCommandHandle(byte[] bytes, String device, int value) {
		super(bytes, device, value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] handle() throws SiDCException {
		// TODO Auto-generated method stub
		byte[] getbyte = new byte[1];
		getbyte = Utils.getInstance().hexToBytes("0x" + Utils.getInstance().makesUpZero(getValue(), 2));
		switch (ServiceName.valueOf(getDevice()).ordinal()) {
		case 0: //SOS
			getBytes()[0] = getbyte[0];
			break;
		case 1: //MUR
			getBytes()[1] = getbyte[0];
			break;
		case 2: //DND
			getBytes()[2] = getbyte[0];
			break;
		case 3: //BUTLER
			getBytes()[3] = getbyte[0];
			break;
		case 4: //DOORLOCK
			getBytes()[4] = getbyte[0];
			break;
		case 5: //BLIND
			getBytes()[5] = getbyte[0];
			break;
		case 6: //VENTILATOR
			getBytes()[6] = getbyte[0];
			break;
		case 7: //CHECKOUT
			getBytes()[7] = getbyte[0];
			break;
		case 8: //DOORTIMEOUT
			getBytes()[8] = getbyte[0];
			break;
		case 9: //LAUNDRY
			getBytes()[9] = getbyte[0];
			break;
		case 10: //PICKUP
			getBytes()[10] = getbyte[0];
			break;
		case 11: //ROOMSERVICE
			getBytes()[11] = getbyte[0];
			break;
		case 12: //SAFE
			getBytes()[12] = getbyte[0];
			break;
		case 13: //WAIT
			getBytes()[13] = getbyte[0];
			break;
		case 14: //DOORSENSOR
			getBytes()[14] = getbyte[0];
			break;
		case 15: //PIR
			getBytes()[15] = getbyte[0];
			break;
		case 16: //PSI
			getBytes()[16] = getbyte[0];
			break;
		case 17: //HUMIDITY
			getBytes()[17] = getbyte[0];
			break;
		};
		return getBytes();
	}

}
