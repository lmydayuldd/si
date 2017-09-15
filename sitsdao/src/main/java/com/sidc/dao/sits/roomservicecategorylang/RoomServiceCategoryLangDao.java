package com.sidc.dao.sits.roomservicecategorylang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;

public class RoomServiceCategoryLangDao {
	/**
	 * @author Joe
	 */
	private RoomServiceCategoryLangDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceCategoryLangDao INSTANCE = new RoomServiceCategoryLangDao();
	}

	public static RoomServiceCategoryLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_category_lang(rcl_roomservice_category_id,rcl_lang,rcl_name,rcl_description,"
			+ "rcl_modify_time,rcl_creation_time) VALUES (?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int categoryId, final String langCode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setString(++i, langCode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("roomservice_category_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String DELETE = "DELETE FROM roomservice_category_lang WHERE rcl_roomservice_category_id = ?;";

	public void delete(final Connection conn, final int categoryId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, categoryId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_NAME = "SELECT rcl_name FROM roomservice_category_lang WHERE rcl_roomservice_category_id = ? AND "
			+ "rcl_lang = ?;";

	public String selectName(final Connection conn, final int categoryId, final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SELECT_NAME);

			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("rcl_name");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

	private final static String SELECT_BY_PARAMETER = "SELECT rcl_lang,rcl_name,rcl_description FROM roomservice_category_lang WHERE"
			+ " rcl_roomservice_category_id = ? ";
	private final String PARAMETER_LANG = "AND rcl_lang = ? ";

	public List<RoomServiceLangBean> select(final Connection conn, final int categoryId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
		try {

			String description = "";
			if (!StringUtils.isBlank(langCode)) {
				description = PARAMETER_LANG;
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			int i = 0;
			psmt.setInt(++i, categoryId);

			if (!StringUtils.isBlank(langCode)) {
				psmt.setString(++i, langCode);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceLangBean(rs.getString("rcl_lang"), rs.getString("rcl_name"),
						rs.getString("rcl_description")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
