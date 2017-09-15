package com.sidc.dao.sits.tokenstaffdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TokenStaffDetailDao {

	private static final class lazyHolder {
		public static TokenStaffDetailDao INSTANCE = new TokenStaffDetailDao();
	}

	public static TokenStaffDetailDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String DELETE_BY_ID = "DELETE FROM token_staff_detail WHERE tsd_staff_id = ? ;";

	public void deleteById(final Connection conn, final String staffId) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_ID);

			int i = 0;
			psmt.setString(++i, staffId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM token_staff_detail WHERE tsd_token_header_id IN (SELECT th_id FROM token_header WHERE NOW() >= th_expired_time AND th_type = 1 );";

	public void deleteByFailure(final Connection conn) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String INSERT = "INSERT INTO token_staff_detail(tsd_token_header_id,tsd_staff_id,tsd_mobile_info_id,tsd_creation_time)"
			+ "VALUES(?,?,?,NOW())";

	public int insert(final Connection conn, final String tokenId, final String staffId, final int mobileInfoId)
			throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, tokenId);
			psmt.setString(++i, staffId);
			psmt.setInt(++i, mobileInfoId);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT = "SELECT tsd_token_header_id FROM token_staff_detail WHERE tsd_staff_id = ? ;";

	public List<String> select(final Connection conn, final String id) throws SQLException {
		PreparedStatement psmt = null;

		List<String> list = new ArrayList<String>();

		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, id);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("tsd_token_header_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE_BY_HEADERID = "DELETE FROM token_staff_detail WHERE tsd_token_header_id = ? ;";

	public void deleteByTokenHeaderId(final Connection conn, final String tokenHeaderId) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_HEADERID);

			int i = 0;
			psmt.setString(++i, tokenHeaderId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_STAFFID_BY_TOKEN = "SELECT tsd_staff_id FROM token_staff_detail WHERE tsd_token_header_id = ? ;";

	public String selectStaffIdByToken(final Connection conn, final String token) throws SQLException {
		PreparedStatement psmt = null;

		String staffId = null;

		try {
			psmt = conn.prepareStatement(SELECT_STAFFID_BY_TOKEN);

			int i = 0;
			psmt.setString(++i, token);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				staffId = rs.getString("tsd_staff_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return staffId;
	}
}
