package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuAgentBehaviorEntity;

public class RcuAgentBehaviorDao {
	private static class LazyHolder {
		public static final RcuAgentBehaviorDao INSTANCE = new RcuAgentBehaviorDao();
	}

	public static RcuAgentBehaviorDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_ALL = "SELECT id,`key` FROM rcu_agent_behavior;";

	public List<RcuAgentBehaviorEntity> selectAll(final Connection conn) throws SQLException {
		List<RcuAgentBehaviorEntity> list = new ArrayList<RcuAgentBehaviorEntity>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_ALL);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RcuAgentBehaviorEntity(rs.getInt("id"), rs.getString("key")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
