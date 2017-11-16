package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.room.RoomDao;

public class RoomManager {

	private RoomManager() {
	}

	private static class LazyHolder {
		public static final RoomManager INSTANCE = new RoomManager();
	}

	public static RoomManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public boolean isExist(final String roomNo) throws SQLException {
		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = RoomDao.getInstance().isExist(conn, roomNo);

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isExist;
	}

}
