/**
 * 
 */
package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuGroupModuleBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RcuDeviceDao;
import com.sidc.dao.ra.RcuDeviceGroupDao;
import com.sidc.dao.ra.response.RcuModule;

/**
 * @author Nandin
 *
 */
public class RCUDeviceManager {

	private RCUDeviceManager() {
	}

	private static class LazyHolder {
		public static final RCUDeviceManager INSTANCE = new RCUDeviceManager();
	}

	public static RCUDeviceManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<RcuModule> listRcuDevice() throws SQLException {

		List<RcuModule> list = new ArrayList<RcuModule>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RcuDeviceDao.getInstance().listRcuDevice(conn);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return list;
	}

	public List<RcuGroupModuleBean> listRcuDeviceGroup() throws SQLException {

		List<RcuGroupModuleBean> list = new ArrayList<RcuGroupModuleBean>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RcuDeviceDao.getInstance().listRcuDeviceGroup(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return list;
	}

	public List<String> listRcuGroup() throws SQLException {

		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RcuDeviceGroupDao.getInstance().listAllGroup(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return list;
	}
}
