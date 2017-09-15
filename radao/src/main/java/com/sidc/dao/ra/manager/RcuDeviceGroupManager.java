package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RcuDeviceDao;

public class RcuDeviceGroupManager {
	private static class LazyHolder {
		public static final RcuDeviceGroupManager INSTANCE = new RcuDeviceGroupManager();
	}

	public static RcuDeviceGroupManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<RcuDeviceEnity> searchDeviceByGroup(final String groupName) throws SQLException {
		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			list = RcuDeviceDao.getInstance().searchByRcuDeviceGroup(conn, groupName);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}
	
	public List<RcuDeviceEnity> searchDeviceByDevices(final String deviceName) throws SQLException {
		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			list = RcuDeviceDao.getInstance().searchByDevice(conn, deviceName);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<RcuDeviceEnity> selectAllDevice() throws SQLException {
		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			list = RcuDeviceDao.getInstance().selectAllDevice(conn);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}
}
