package com.sidc.dao.sits.activityfeelang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeLangBean;

public class ActivityFeeLangDao {

	private static final class lazyHolder {
		public static ActivityFeeLangDao INSTANCE = new ActivityFeeLangDao();
	}

	public static ActivityFeeLangDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_fee_lang(afl_activity_fee_id,afl_lang,afl_name,afl_creation_time) "
			+ "VALUES(?,?,?,now());";

	public int insert(final Connection conn, final int feeId, final String langcode, final String name)
			throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, feeId);
			psmt.setString(++i, langcode);
			psmt.setString(++i, name);
			psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_fee_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT afl_lang,afl_name FROM activity_fee_lang WHERE afl_activity_fee_id = ? ";

	public List<ActivityFeeLangBean> select(final Connection conn, final int feeId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityFeeLangBean> list = new ArrayList<ActivityFeeLangBean>();
		try {
			String description = "";
			if (!StringUtils.isBlank(langCode)) {
				description = " AND afl_lang = '" + langCode + "'";
			}

			psmt = conn.prepareStatement(SELECT + description);
			int i = 0;
			psmt.setInt(++i, feeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityFeeLangBean(rs.getString("afl_lang"), rs.getString("afl_name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SELECT_BY_LANGCODE = "SELECT afl_lang,afl_name FROM activity_fee_lang WHERE afl_activity_fee_id = ? "
			+ " AND afl_lang = ?";

	public ActivityFeeLangBean selectByLangCode(final Connection conn, final int feeId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		ActivityFeeLangBean entity = null;
		try {

			psmt = conn.prepareStatement(SELECT_BY_LANGCODE);
			int i = 0;
			psmt.setInt(++i, feeId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new ActivityFeeLangBean(rs.getString("afl_lang"), rs.getString("afl_name"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return entity;
	}

	private final static String SELECT_BY_ID = "SELECT afl_lang,afl_name FROM activity_fee_lang WHERE afl_activity_fee_id = ? ;";

	public List<ActivityFeeLangBean> select(final Connection conn, final int feeId) throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityFeeLangBean> list = new ArrayList<ActivityFeeLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);
			int i = 0;
			psmt.setInt(++i, feeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityFeeLangBean(rs.getString("afl_lang"), rs.getString("afl_name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String DELETE = "DELETE FROM activity_fee_lang WHERE afl_activity_fee_id = ? ; ";

	public void delete(final Connection conn, final int feeId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, feeId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

}
