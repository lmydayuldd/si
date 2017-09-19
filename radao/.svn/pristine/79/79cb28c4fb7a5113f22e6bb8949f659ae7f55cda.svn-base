package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RcuGroupModeDao;

public class RcuGroupModelManager {
	private static class LazyHolder {
		public static final RcuGroupModelManager INSTANCE = new RcuGroupModelManager();
	}

	public static RcuGroupModelManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public Integer searchRcuModel(final int rcuGroupId, final int rcuModelId) throws SQLException {
		Integer id = 0;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			id = RcuGroupModeDao.getInstance().searchWithRcuGroup(conn, rcuGroupId, rcuModelId);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return id;
	}
}
