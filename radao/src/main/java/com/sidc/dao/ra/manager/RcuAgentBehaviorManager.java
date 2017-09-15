package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuAgentBehaviorEntity;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RcuAgentBehaviorDao;

public class RcuAgentBehaviorManager {
	private static class LazyHolder {
		public static final RcuAgentBehaviorManager INSTANCE = new RcuAgentBehaviorManager();
	}

	public static RcuAgentBehaviorManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<RcuAgentBehaviorEntity> selectAll() throws SQLException {
		List<RcuAgentBehaviorEntity> list = new ArrayList<RcuAgentBehaviorEntity>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			list = RcuAgentBehaviorDao.getInstance().selectAll(conn);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}
}
