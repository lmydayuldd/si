package com.sidc.zhongshan.bean;

import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.handler.ServiceCommandHandle;

/**
 * 
 * @author Allen Huang
 *
 */
public class ServiceHex {

	public static byte[] command(byte[] bytes, final String device, final int value) throws SiDCException {
		return new ServiceCommandHandle(bytes, device, value).handle();
	}
}
