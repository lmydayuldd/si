/**
 * 
 */
package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RoomInfoEnity;
import com.sidc.dao.bean.RoomModuelDevice;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RCUGroupDao;
import com.sidc.dao.ra.RCUGroupDeviceDao;
import com.sidc.dao.ra.RoomRcuDao;
import com.sidc.dao.ra.response.RoomRcuEnity;

/**
 * @author Nandin
 *
 */
public class RoomRCUManager {

	private RoomRCUManager() {
	}

	private static class LazyHolder {
		public static final RoomRCUManager INSTANCE = new RoomRCUManager();
	}

	public static RoomRCUManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<RoomInfoEnity> listRoomRcuInfo() throws SQLException {
		List<RoomInfoEnity> result = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RoomRcuDao.getInstance().listRoomRcuInfo(conn);

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
