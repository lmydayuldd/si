package com.sidc.sits.logical.utils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
	private DateFormat dateFromat;

	public void setDateFromat(DateFormat dateFromat) {
		this.dateFromat = dateFromat;
	}

	public DateTimeUtils(DateFormat dateFromat) {
		super();
		this.dateFromat = dateFromat;
	}

	/***
	 * 檢查是否為時間格式
	 * 
	 * @param datetime
	 * @return
	 */
	public boolean isDate(final String dateTime) {
		dateFromat.setLenient(false);
		ParsePosition pos = new ParsePosition(0);
		Date date = dateFromat.parse(dateTime, pos);

		if (date == null || pos.getErrorIndex() > 0) {
			return false;
		}
		if (pos.getIndex() != dateTime.length()) {
			return false;
		}
		if (dateFromat.getCalendar().get(Calendar.YEAR) > 9999) {
			return false;
		}
		return true;
	}

	/***
	 * 檢查 起始時間 < 結束時間
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean checkSequence(final String startTime, final String endTime) {
		dateFromat.setLenient(false);
		Date startDate = dateFromat.parse(startTime, new ParsePosition(0));
		Date endDate = dateFromat.parse(endTime, new ParsePosition(0));

		if (startDate.getTime() > endDate.getTime()) {
			return false;
		}
		return true;
	}
}
