package com.sidc.dao.sits.roomserviceitemlang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;

public class RoomServiceItemLangDao {
	/**
	 * @author Joe
	 */
	private RoomServiceItemLangDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceItemLangDao INSTANCE = new RoomServiceItemLangDao();
	}

	public static RoomServiceItemLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_item_lang(ril_rommservice_item_id,ril_lang,ril_name,ril_description,"
			+ "ril_modify_time,ril_creation_time) VALUES (?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int itemId, final String langCode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langCode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("roomservice_item_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String DELETE = "DELETE FROM roomservice_item_lang WHERE ril_rommservice_item_id = ?;";

	public void delete(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, itemId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_NAME = "SELECT ril_name FROM roomservice_item_lang WHERE ril_rommservice_item_id = ? AND ril_lang = ?;";

	public String selectName(final Connection conn, final int itemId, final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SELECT_NAME);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("ril_name");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

	private final static String SELECT_NAME_TOP1 = "SELECT ril_name FROM roomservice_item_lang WHERE ril_rommservice_item_id = ?"
			+ " LIMIT 1;";

	/**
	 * 選取db中的任一筆資料
	 * 
	 * @param conn
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public String selectName(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SELECT_NAME_TOP1);

			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("ril_name");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

	private final static String SELECT_BY_PARAMETER = "SELECT ril_lang,ril_name,ril_description FROM roomservice_item_lang WHERE"
			+ " ril_rommservice_item_id = ? ";
	private final String PARAMETER_LANG = " ril_lang = ? ";

	public List<RoomServiceLangBean> select(final Connection conn, final int itemId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
		try {

			String description = "";

			int x = 0;

			if (!StringUtils.isBlank(langCode)) {
				description = formatWhereDescription(x++, PARAMETER_LANG, description);
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			int i = 0;
			psmt.setInt(++i, itemId);

			if (!StringUtils.isBlank(langCode)) {
				psmt.setString(++i, langCode);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceLangBean(rs.getString("ril_lang"), rs.getString("ril_name"),
						rs.getString("ril_description")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private String formatWhereDescription(int i, final String sqlWhereDescription, String description) {
		if (i == 0) {
			description += sqlWhereDescription;
		} else {
			description += "AND" + sqlWhereDescription;
		}
		return description;
	}

}
