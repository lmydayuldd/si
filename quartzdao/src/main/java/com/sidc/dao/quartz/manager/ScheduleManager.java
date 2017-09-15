package com.sidc.dao.quartz.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.schedule.ScheduleDao;
import com.sidc.quartz.api.request.ScheduleUpdateInfoRequest;
import com.sidc.quartz.api.response.ScheduleInfoResponse;
import com.sidc.quartz.api.response.ScheduleStatusResponse;

public class ScheduleManager {
	private static class LazyHolder {
		public static final ScheduleManager INSTANCE = new ScheduleManager();
	}

	public static ScheduleManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void update(final ScheduleUpdateInfoRequest enity) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			ScheduleDao.getInstance().update(conn, enity);
			conn.commit();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void updateInfo(final ScheduleUpdateInfoRequest enity) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			ScheduleDao.getInstance().updateInfo(conn, enity.getJobname(), enity.getExecutiontime(),
					enity.getDescription());
			conn.commit();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public ScheduleStatusResponse enabled(final String jobName) throws SQLException {
		Connection conn = null;
		ScheduleStatusResponse entity = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			entity = ScheduleDao.getInstance().enabled(conn, jobName);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}

	public ScheduleInfoResponse select(final String jobName) throws SQLException {
		Connection conn = null;
		ScheduleInfoResponse entity = null;

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
