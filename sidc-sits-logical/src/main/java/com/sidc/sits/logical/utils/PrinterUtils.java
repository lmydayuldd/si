package com.sidc.sits.logical.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceBackendOrderInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineInfoBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopOrderLineBean;
import com.sidc.blackcore.api.sits.shop.response.ShoppingBackendOrderResponse;
import com.sidc.configuration.conf.Env;
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
			HttpClientUtils.sendPostWithPrint(sitsUrl, printerName, data, signature(printerName[0]));
		} catch (Exception e) {
			throw new SiDCException(APIStatus.HTTP_METHOD_FAIL,
					"send post fail," + sitsUrl + PageList.PRINT_DATA + ".");
		}
	}

	private String signature(final String data) throws Exception {
		final AesEncrypt encrypt = new AesEncrypt("sidc-sits");
		return encrypt.encrypt(data).trim();
	}

	/**
	 * 套表....
	 * 
	 * @param formatStr
	 * @param orderEntity
	 * @return
	 * @throws SiDCException
	 */
	public String replaceTable(final RoomServiceBackendOrderInfoBean orderEntity) throws SiDCException {
		String formatStr = null;
		// 表單格式
		try {
			formatStr = FileUtils.readFileToString(new File(Env.SYSTEM_DEF_PATH + Env.PRINT_FORMAT),
					CharEncoding.UTF_8);
		} catch (IOException e) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST,
					"not find printer file,path:" + Env.SYSTEM_DEF_PATH + Env.PRINT_FORMAT);
		}

		// #{each} 代筆 item 先取出來
		final String eachFormat = formatStr.substring(formatStr.indexOf("#{each}") + 8, formatStr.indexOf("#{/each}"));

		// item 替換成 #{eachData} 下面code直接取代
		formatStr = formatStr.replace(
				formatStr.substring(formatStr.indexOf("#{each}"), formatStr.indexOf("#{/each}") + 8), "#{eachData}");
		String eachData = "";

		// format item資料
		int i = 1;
		for (final RoomServiceOrderLineInfoBean itemEntity : orderEntity.getItemlist()) {
			String formatData = eachFormat;
			formatData = formatData.replace("#{index}", String.valueOf(i++));
			formatData = formatData.replace("#{itemName}",
					StringUtils.isBlank(itemEntity.getItemname()) ? "" : itemEntity.getItemname());
			formatData = formatData.replace("#{price}", String.valueOf(itemEntity.getAmount()));
			formatData = formatData.replace("#{qty}", String.valueOf(itemEntity.getQty()));
			formatData = formatData.replace("#{subTotal}",
					String.valueOf(itemEntity.getQty() * itemEntity.getAmount()));
			eachData += formatData;
		}

		final String guestFistName = StringUtils.isBlank(orderEntity.getGuestfirstname()) ? ""
				: orderEntity.getGuestfirstname();

		final String guestLastName = StringUtils.isBlank(orderEntity.getGuestlastname()) ? ""
				: orderEntity.getGuestlastname();

		// format 表頭
		formatStr = formatStr.replace("#{hotelName}", "");
		formatStr = formatStr.replace("#{orderId}", String.valueOf(orderEntity.getId()));
		formatStr = formatStr.replace("#{roomNo}", orderEntity.getRoomno());
		formatStr = formatStr.replace("#{guestName}", guestLastName + " " + guestFistName);
		formatStr = formatStr.replace("#{total}", String.valueOf(orderEntity.getAmount()));
		formatStr = formatStr.replace("#{creationTime}", orderEntity.getCreatetime());
		formatStr = formatStr.replace("#{eachData}", eachData);

		return formatStr;
	}

	public String replaceTable(final ShoppingBackendOrderResponse orderEntity) throws SiDCException {
		String formatStr = null;
		// 表單格式
		try {
			formatStr = FileUtils.readFileToString(new File(Env.SYSTEM_DEF_PATH + Env.PRINT_FORMAT),
					CharEncoding.UTF_8);
		} catch (IOException e) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST,
					"not find printer file,path:" + Env.SYSTEM_DEF_PATH + Env.PRINT_FORMAT);
		}

		// #{each} 代筆 item 先取出來
		final String eachFormat = formatStr.substring(formatStr.indexOf("#{each}") + 8, formatStr.indexOf("#{/each}"));

		// item 替換成 #{eachData} 下面code直接取代
		formatStr = formatStr.replace(
				formatStr.substring(formatStr.indexOf("#{each}"), formatStr.indexOf("#{/each}") + 8), "#{eachData}");
		String eachData = "";

		// format item資料
		int i = 1;
		for (final ShopOrderLineBean itemEntity : orderEntity.getItemlist()) {
			String formatData = eachFormat;
			formatData = formatData.replace("#{index}", String.valueOf(i++));
			formatData = formatData.replace("#{itemName}",
					StringUtils.isBlank(itemEntity.getItemname()) ? "" : itemEntity.getItemname());
			formatData = formatData.replace("#{price}", String.valueOf(itemEntity.getAmount()));
			formatData = formatData.replace("#{qty}", String.valueOf(itemEntity.getQty()));
			formatData = formatData.replace("#{subTotal}",
					String.valueOf(itemEntity.getQty() * itemEntity.getAmount()));
			eachData += formatData;
		}

		final String guestFistName = StringUtils.isBlank(orderEntity.getGuestfirstname()) ? ""
				: orderEntity.getGuestfirstname();

		final String guestLastName = StringUtils.isBlank(orderEntity.getGuestlasttname()) ? ""
				: orderEntity.getGuestlasttname();

		// format 表頭
		formatStr = formatStr.replace("#{hotelName}", "");
		formatStr = formatStr.replace("#{orderId}", String.valueOf(orderEntity.getId()));
		formatStr = formatStr.replace("#{roomNo}", orderEntity.getRoomno());
		formatStr = formatStr.replace("#{guestName}", guestLastName + " " + guestFistName);
		formatStr = formatStr.replace("#{total}", String.valueOf(orderEntity.getAmount()));
		formatStr = formatStr.replace("#{creationTime}", orderEntity.getCreatetime());
		formatStr = formatStr.replace("#{eachData}", eachData);

		return formatStr;
	}

}
