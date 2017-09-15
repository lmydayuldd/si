/**
 * 
 */
package com.sidc.dao.ra.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuGroupEnity;

/**
 * @author Nandin
 *
 */
public class RcuGroupDao {

	private RcuGroupDao() {
	}

	private static class LazyHolder {
		public static final RcuGroupDao INSTANCE = new RcuGroupDao();
	}

	public static RcuGroupDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO room_rcu( roomno, rcu_group_id, createdtime) "
			+ " VALUES(?, ?, now())";

	public void insert(Connection conn, final String roomno, final int rcu_group_id) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, roomno);
			psmt.setInt(++i, rcu_group_id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String UPDATE = "UPDATE room_rcu SET rcu_group_id = ? WHERE roomno = ?";

	public void update(Connection conn, final String roomno, final int rcu_group_id) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, rcu_group_id);
			psmt.setString(++i, roomno);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String DELETE = "DELETE FROM room_rcu WHERE roomno=?";

	public void delete(Connection conn, final String roomno) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setString(++i, roomno);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String LIST_RCU_GROUP = "SELECT id, name FROM rcu_group ";

	public List<RcuGroupEnity> listRcuGroup(final Connection conn) throws SQLException {

		List<RcuGroupEnity> list = new ArrayList<RcuGroupEnity>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_RCU_GROUP);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RcuGroupEnity(rs.getInt("id"), rs.getString("name")));
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String EXIST_RCU_GROUP = "SELECT count(id) as total FROM rcu_group where id=? ";

	public boolean existRCUGroup(final Connection conn, final int rcu_group_id) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(EXIST_RCU_GROUP);

			int i = 0;
			psmt.setInt(++i, rcu_group_id);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total");

				return total > 0;
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return false;
	}

}
