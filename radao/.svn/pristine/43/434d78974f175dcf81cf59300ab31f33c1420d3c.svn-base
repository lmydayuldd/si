/**
 * 
 */
package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RcuDeviceGroupDao {

	private RcuDeviceGroupDao() {
	}

	private static class LazyHolder {
		public static final RcuDeviceGroupDao INSTANCE = new RcuDeviceGroupDao();
	}

	public static RcuDeviceGroupDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String LIST_ALL_GROUP = "SELECT name FROM rcu_device_group;";

	public List<String> listAllGroup(final Connection conn) throws SQLException {

		List<String> list = new ArrayList<String>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_ALL_GROUP);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("name"));
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
