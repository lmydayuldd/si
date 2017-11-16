/**
 * 
 */
package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sidc.blackcore.api.ra.rcugroup.bean.RoomRcuInsertBean;
import com.sidc.dao.bean.RoomModuelDevice;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RCUGroupDao;
import com.sidc.dao.ra.RCUGroupDeviceDao;
import com.sidc.dao.ra.RcuGroupModeDao;
import com.sidc.dao.ra.RcuModeDao;
import com.sidc.dao.ra.RcuModeDeviceDao;
import com.sidc.dao.ra.RcuModeLangDao;
import com.sidc.dao.ra.RoomRcuDao;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.dao.ra.room.RcuGroupDao;

/**
 * @author Nandin
 *
 */
public class RCUManager {

	private RCUManager() {
	}

	private static class LazyHolder {
		public static final RCUManager INSTANCE = new RCUManager();
	}

	public static RCUManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void insertRCU(final List<RoomModuelDevice> modules) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RCUGroupDeviceDao.getInstance().delete(conn);

			for (RoomModuelDevice roomModuleBean : modules) {
				RCUGroupDao.getInstance().insert(conn, roomModuleBean.getGroupId(), roomModuleBean.getGroupName());
				RCUGroupDao.getInstance().update(conn, roomModuleBean.getGroupId(), roomModuleBean.getGroupName());
				RCUGroupDeviceDao.getInstance().insert(conn, roomModuleBean.getGroupId(), roomModuleBean.getDeviceId());
			}

			conn.commit();

		} catch (Exception ex) {
			conn.rollback();
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void insertRCU(final List<RoomModuelDevice> modules, final List<RoomRcuInsertBean> rooms)
			throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RoomRcuDao.getInstance().delete(conn);
			RcuModeLangDao.getInstance().deleteByNotDefualt(conn);
			RcuModeDeviceDao.getInstance().delete(conn);
			RCUGroupDeviceDao.getInstance().delete(conn);
			RcuGroupModeDao.getInstance().deleteByNotDefault(conn);
			RcuModeDao.getInstance().deleteByNotDefualt(conn);

			for (final RoomModuelDevice roomModuleBean : modules) {
				RCUGroupDao.getInstance().insert(conn, roomModuleBean.getGroupId(), roomModuleBean.getGroupName());
				RCUGroupDao.getInstance().update(conn, roomModuleBean.getGroupId(), roomModuleBean.getGroupName());
				RCUGroupDeviceDao.getInstance().insert(conn, roomModuleBean.getGroupId(), roomModuleBean.getDeviceId());
			}

			for (final RoomRcuInsertBean entity : rooms) {
				for (final String roomNo : entity.getRooms()) {
					RcuGroupDao.getInstance().insert(conn, roomNo, entity.getGroupid());
				}
			}

			conn.commit();

		} catch (Exception ex) {
			conn.rollback();
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public List<RoomRcuEnity> listRoomRCU() throws SQLException {
		List<RoomRcuEnity> result = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RoomRcuDao.getInstance().listRoomRcuDevice(conn);

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}
}
