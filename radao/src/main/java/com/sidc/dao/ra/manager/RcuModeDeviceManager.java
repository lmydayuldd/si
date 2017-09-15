/**
 * 
 */
package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeDeviceInsertListEntity;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeDeviceResponse;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RcuModeDeviceDao;

public class RcuModeDeviceManager {

	private RcuModeDeviceManager() {
	}

	private static class LazyHolder {
		public static final RcuModeDeviceManager INSTANCE = new RcuModeDeviceManager();
	}

	public static RcuModeDeviceManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<RcuModeDeviceResponse> searchDevice(final int modeId) throws SQLException {

		List<RcuModeDeviceResponse> list = new ArrayList<RcuModeDeviceResponse>();

		Connection conn = null; 
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			list = RcuModeDeviceDao.getInstance().searchByModeId(conn, modeId);
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public void insert(final int modeId, final List<RcuModeDeviceInsertListEntity> deviceList) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (RcuModeDeviceInsertListEntity entity : deviceList) {
				RcuModeDeviceDao.getInstance().insert(conn, modeId, entity.getDeviceId());
			}

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void delete(final int modeId, final List<RcuModeDeviceInsertListEntity> deviceList) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (RcuModeDeviceInsertListEntity entity : deviceList) {
				RcuModeDeviceDao.getInstance().delete(conn, modeId, entity.getDeviceId());
			}

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void deleteAndInsert(final int modeId, final List<RcuModeDeviceInsertListEntity> deviceList)
			throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RcuModeDeviceDao.getInstance().delete(conn, modeId);

			for (RcuModeDeviceInsertListEntity entity : deviceList) {
				RcuModeDeviceDao.getInstance().insert(conn, modeId, entity.getDeviceId());
			}

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

}
