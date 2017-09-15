package com.sidc.dao.sits.activitytypelang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityTypeBean;

public class ActivityTypeLangDao {

	private static final class lazyHolder {
		public static ActivityTypeLangDao INSTANCE = new ActivityTypeLangDao();
	}

	public static ActivityTypeLangDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_type_lang(atl_activity_type_id,atl_lang,atl_name,atl_description,atl_creation_time) "
			+ "VALUES(?,?,?,?,now()); ";

	public int insert(final Connection conn, final int typeId, final String langCode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, typeId);
			psmt.setString(++i, langCode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_type_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_LANGCODE = "SELECT atl_lang,atl_name,atl_description FROM activity_type_lang "
			+ "WHERE atl_activity_type_id = ? AND atl_lang = ? ;";

	public List<ActivityTypeBean> selectByLangCode(final Connection conn, final int typeId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityTypeBean> list = new ArrayList<ActivityTypeBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_LANGCODE);

			int i = 0;
			psmt.setInt(++i, typeId);
			psmt.setString(++i, langCode);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityTypeBean(rs.getString("atl_lang"), rs.getString("atl_name"),
						rs.getString("atl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT atl_lang,atl_name,atl_description FROM activity_type_lang "
			+ "WHERE atl_activity_type_id = ? ;";

	public List<ActivityTypeBean> selectById(final Connection conn, final int typeId) throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityTypeBean> list = new ArrayList<ActivityTypeBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, typeId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityTypeBean(rs.getString("atl_lang"), rs.getString("atl_name"),
						rs.getString("atl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM activity_type_lang WHERE atl_activity_type_id = ?;";

	public void delete(final Connection conn, final int typeId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, typeId);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
