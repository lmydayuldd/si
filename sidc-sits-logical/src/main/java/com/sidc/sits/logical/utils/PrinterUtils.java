package com.sidc.sits.logical.utils;

import java.sql.SQLException;

import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.utils.encrypt.AesEncrypt;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class PrinterUtils {

	/**
	 * 通知sits列印,sits需要5.32
	 * 
	 * @param data
	 * @throws SQLException
	 * @throws SiDCException
	 */
	public void printer(final String data) throws SQLException, SiDCException {
		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());

		// 印表機資料
		final String printerName[] = SystemPropertiesManager.getInstance()
				.findPropertiesMessage("system.printer.food.list").split(",");

		try {
			HttpClientUtils.sendPostWithPrint(sitsUrl, printerName, new String(data.getBytes(), "UTF-8"),
					signature(printerName[0]));
		} catch (Exception e) {
			throw new SiDCException(APIStatus.HTTP_METHOD_FAIL,
					"send post fail," + sitsUrl + PageList.PRINT_DATA + ".");
		}
	}

	private String signature(final String data) throws Exception {
		final AesEncrypt encrypt = new AesEncrypt("sidc-sits");
		return encrypt.encrypt(data).trim();
	}

}
