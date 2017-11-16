package com.sidc.dao.quartz.manager;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.schedule.ScheduleDao;
import com.sidc.quartz.api.request.ScheduleUpdateDataRequest;
import com.sidc.quartz.api.response.ScheduleDataResponse;
import com.sidc.quartz.api.response.ScheduleStatusResponse;

public class ScheduleManager {
	private static class LazyHolder {
		public static final ScheduleManager INSTANCE = new ScheduleManager();
	}

	public static ScheduleManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void update(final ScheduleUpdateDataRequest enity) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			
			String setInfo = "";
			if (!StringUtils.isBlank(enity.getGroupname())) {
				if (!setInfo.equals("")) setInfo += ", ";
				setInfo += "s_group = '" + enity.getGroupname() + "'";
			}
			if (StringUtils.isNumeric(String.valueOf(enity.getStatus()))) {
				if (!setInfo.equals("")) setInfo += ", ";
				setInfo += "s_status = " + enity.getStatus();
			}
			if (!StringUtils.isBlank(enity.getExecutiontime())) {
				if (!setInfo.equals("")) setInfo += ", ";
				setInfo += "s_execution_time = '" + enity.getExecutiontime() + "'";
			}
			if (!StringUtils.isBlank(enity.getDescription())) {
				if (!setInfo.equals("")) setInfo += ", ";
				setInfo += "s_description = '" + enity.getDescription() + "'";
			}
			if (!StringUtils.isBlank(enity.getCommands())) {
				if (!setInfo.equals("")) setInfo += ", ";
				setInfo += "s_commands = '" + enity.getCommands() + "'";
			}
			
			ScheduleDao.getInstance().update(conn, setInfo, enity.getJobname());
			conn.commit();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public ScheduleStatusResponse isEnabled(final String jobName) throws SQLException {
		Connection conn = null;
		ScheduleStatusResponse entity = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			entity = ScheduleDao.getInstance().isEnabled(conn, jobName);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}

	public ScheduleDataResponse select(final String jobName) throws SQLException {
		Connection conn = null;
		ScheduleDataResponse entity = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			entity = ScheduleDao.getInstance().select(conn, jobName);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}
}
