package com.sidc.dao.sits.sparecategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.spare.response.SpareCategoryResponse;

public class SpareCategoryDao {
	/**
	 * @author Joe
	 */
	private SpareCategoryDao() {
	}

	private static class LazyHolder {
		public static final SpareCategoryDao INSTANCE = new SpareCategoryDao();
	}

	public static SpareCategoryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO spare_category(sc_status,sc_sequence,sc_modify_time,sc_creation_time) "
			+ "VALUES (?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int status, final int sequence) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, sequence);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("spare_category insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE spare_category SET sc_status = ?,sc_sequence = ?,sc_modify_time = NOW() "
			+ "WHERE sc_id = ? ; ";

	public void update(final Connection conn, final int categoryId, final int status, final int sequence)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, sequence);
			psmt.setInt(++i, categoryId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT sc_id FROM spare_category WHERE sc_id = ?";

	public boolean isExist(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isPass = true;
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT = "SELECT sc_id,sc_status,sc_sequence,DATE_FORMAT(sc_creation_time,'%Y/%m/%d %H:%i')AS "
			+ "sc_creation_time FROM spare_category ";

	public List<SpareCategoryResponse> selectCategory(final Connection conn, final int categoryId, final int status)
			throws SQLException {

		PreparedStatement psmt = null;
		List<SpareCategoryResponse> list = new ArrayList<SpareCategoryResponse>();
		try {
			String parameter = "WHERE";
			int x = 0;
			if (categoryId > 0) {
				parameter = formatWhereDescription(x++, " sc_id = " + categoryId, parameter);
			}
			if (status >= 0) {
				parameter = formatWhereDescription(x++, " sc_status = " + status, parameter);
			}

			if (parameter.equals("WHERE")) {
				parameter = "";
			}

			psmt = conn.prepareStatement(SELECT + parameter);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new SpareCategoryResponse(rs.getInt("sc_id"), rs.getInt("sc_status"), rs.getInt("sc_sequence"),
						rs.getString("sc_creation_time")));
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
			description += " AND" + sqlWhereDescription;
		}
		return description;
	}

}
