package com.sidc.dao.sits.activityheaderlang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityLangBean;

public class ActivityHeaderLangDao {

	private static final class lazyHolder {
		public static ActivityHeaderLangDao INSTANCE = new ActivityHeaderLangDao();
	}

	public static ActivityHeaderLangDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_header_lang(ahl_activity_header_id,ahl_lang,ahl_title,ahl_content,ahl_location,ahl_creation_time)"
			+ " VALUES(?,?,?,?,?,now());";

	public int insert(final Connection conn, final int activityId, final String langCode, final String title,
			final String content, final String location) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setString(++i, langCode);
			psmt.setString(++i, title);
			psmt.setString(++i, content);
			psmt.setString(++i, location);

			psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_header_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_LANGCODE = "SELECT ahl_lang,ahl_title,ahl_content,ahl_location FROM activity_header_lang "
			+ "WHERE ahl_activity_header_id = ? ";

	public List<ActivityLangBean> selectByLangCode(final Connection conn, final int activityId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityLangBean> list = new ArrayList<ActivityLangBean>();
		try {
			String description = "";

			if (!StringUtils.isBlank(langCode)) {
				description = " AND ahl_lang = '" + langCode + "'";
			}

			psmt = conn.prepareStatement(SELECT_BY_LANGCODE + description);

			int i = 0;
			psmt.setInt(++i, activityId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityLangBean(rs.getString("ahl_lang"), rs.getString("ahl_title"),
						rs.getString("ahl_content"), rs.getString("ahl_location")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SELECT_BY_LANG = "SELECT ahl_lang,ahl_title,ahl_content,ahl_location FROM activity_header_lang "
			+ "WHERE ahl_activity_header_id = ? AND ahl_lang = ? ";

	public ActivityLangBean selectByLang(final Connection conn, final int activityId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		ActivityLangBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_LANG);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new ActivityLangBean(rs.getString("ahl_lang"), rs.getString("ahl_title"),
						rs.getString("ahl_content"), rs.getString("ahl_location"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return entity;
	}

	private final static String DELETE = "DELETE FROM activity_header_lang WHERE ahl_activity_header_id = ?;";

	public void delete(final Connection conn, final int activityId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, activityId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ID = "SELECT ahl_lang,ahl_title,ahl_content,ahl_location FROM activity_header_lang "
			+ "WHERE ahl_activity_header_id = ? ;";

	public List<ActivityLangBean> selectById(final Connection conn, final int activityId) throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityLangBean> list = new ArrayList<ActivityLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, activityId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityLangBean(rs.getString("ahl_lang"), rs.getString("ahl_title"),
						rs.getString("ahl_content"), rs.getString("ahl_location")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}
}
