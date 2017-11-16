package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuDefaultModeResponse;

/**
 * 
 * @author Allen Huang
 *
 */
public class RcuModeDao {

	private RcuModeDao() {
	}

	private static class LazyHolder {
		public static final RcuModeDao INSTANCE = new RcuModeDao();
	}

	public static RcuModeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_MODE = "SELECT R.room_no, M.content FROM rcu_room_mode R LEFT JOIN rcu_mode_agent A "
			+ "ON R.rcu_mode_id = A.rcu_mode_id LEFT JOIN rcu_mode M ON A.rcu_mode_id = M.id "
			+ "WHERE R.room_no = ? AND M.keyname = ?";

	public StringBuilder selectMode(final Connection conn, final String roomno, final String keyname)
			throws SQLException {

		StringBuilder builder = new StringBuilder();
		PreparedStatement psmt = null;
		try {
			int i = 0;
			psmt = conn.prepareStatement(SELECT_MODE);
			psmt.setString(++i, roomno);
			psmt.setString(++i, keyname);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				builder.append(rs.getString("content"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return builder;
	}

	private final static String SELECT_COMMON_MODE = "SELECT content FROM rcu_mode WHERE keyname = ?";

	public StringBuilder selectCommonMode(final Connection conn, final String keyname) throws SQLException {

		StringBuilder builder = new StringBuilder();
		PreparedStatement psmt = null;
		try {
			int i = 0;
			psmt = conn.prepareStatement(SELECT_COMMON_MODE);
			psmt.setString(++i, keyname);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				builder.append(rs.getString("content"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return builder;
	}

	private final static String SEARCH_BY_ID = "SELECT id, keyname, content FROM rcu_mode WHERE id = ?";

	public String searchById(final Connection conn, final int id) throws SQLException {

		String content = null;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BY_ID);
			int i = 0;
			psmt.setInt(++i, id);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				content = rs.getString("content");
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return content;
	}

	private final static String SEARCH_BY_KEY = "SELECT id FROM rcu_mode WHERE keyname = ?";

	public int searchIdByKeyName(final Connection conn, final String keyName) throws SQLException {

		int id = -999;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BY_KEY);
			int i = 0;
			psmt.setString(++i, keyName);

			final ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SEARCH_DEFAULT_MODE = "SELECT id, keyname, content, timer FROM rcu_mode WHERE status = 1 ORDER BY id ASC;";

	public List<RcuDefaultModeResponse> searchDefaultMode(final Connection conn) throws SQLException {

		List<RcuDefaultModeResponse> list = new ArrayList<RcuDefaultModeResponse>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_DEFAULT_MODE);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RcuDefaultModeResponse(rs.getInt("id"), rs.getString("keyname"), rs.getString("content"),
						rs.getInt("timer"), rs.getInt("status")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SEARCH_ALL_MODE = "SELECT id, keyname, content, timer,status FROM rcu_mode ORDER BY id ASC;";

	public List<RcuDefaultModeResponse> searchAllMode(final Connection conn) throws SQLException {

		List<RcuDefaultModeResponse> list = new ArrayList<RcuDefaultModeResponse>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_ALL_MODE);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RcuDefaultModeResponse(rs.getInt("id"), rs.getString("keyname"), rs.getString("content"),
						rs.getInt("timer"), rs.getInt("status")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String INSERT = "INSERT INTO rcu_mode(id,keyname,content,createdtime,modifiedtime)VALUES(?,?,?,NOW(),NOW());";

	public void insert(final Connection conn, final int id, final String keyName, final String content)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);
			int i = 0;
			psmt.setInt(++i, id);
			psmt.setString(++i, keyName);
			psmt.setString(++i, content);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String INSERT_INITIAL = "INSERT INTO rcu_mode(id,keyname,content,status,timer,createdtime,modifiedtime)"
			+ "VALUES(?,?,?,?,?,NOW(),NOW());";

	public void insertInitial(final Connection conn, final int id, final String keyName, final int timer)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_INITIAL);
			int i = 0;
			psmt.setInt(++i, id);
			psmt.setString(++i, keyName);
			psmt.setString(++i, " ");
			psmt.setInt(++i, 1);
			psmt.setInt(++i, timer);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM rcu_mode WHERE id = ?;";

	public void delete(final Connection conn, final int modeId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, modeId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_All = "DELETE FROM rcu_mode;";

	public void deleteAll(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_All);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_IS_NOT_DEFAULT = "DELETE FROM rcu_mode WHERE status != 1;";

	public void deleteByNotDefualt(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_IS_NOT_DEFAULT);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_DEFAULT = "SELECT status FROM rcu_mode WHERE id = ? AND status = 1;";

	public boolean isDefault(final Connection conn, final int modeId) throws SQLException {

		boolean isDefault = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_DEFAULT);

			int i = 0;
			psmt.setInt(++i, modeId);

			final ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isDefault = true;
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return isDefault;
	}

	private final static String FIND_LAST_ID = "SELECT id FROM rcu_mode ORDER BY id DESC LIMIT 1;";

	public int findLastId(final Connection conn) throws SQLException {

		int id = -999;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(FIND_LAST_ID);

			final ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}
}
