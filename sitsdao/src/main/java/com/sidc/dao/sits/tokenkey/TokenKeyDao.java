package com.sidc.dao.sits.tokenkey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TokenKeyDao {

	private static final class lazyHolder {
		public static TokenKeyDao INSTANCE = new TokenKeyDao();
	}

	public static TokenKeyDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO token_key(tk_token_header_id,tk_token_detail_id,tk_private_key) VALUES (?,?,?) ;";

	public String insert(final Connection conn, final String tokenId, final int detailId) throws SQLException {
		PreparedStatement psmt = null;

		final String uuid = UUID.randomUUID().toString();

		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, tokenId);
			psmt.setInt(++i, detailId);
			psmt.setString(++i, uuid);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return uuid;
	}

	private final static String DELETE = "DELETE FROM token_key WHERE tk_token_header_id IN (SELECT  th_id FROM token_header WHERE NOW() "
			+ "> th_expired_time);";

	public void delete(final Connection conn) throws SQLException {
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

	private final static String DELETE_BY_ROOM = "DELETE FROM token_key WHERE tk_token_detail_id IN (SELECT trd_id FROM token_room_detail "
			+ "WHERE trd_roomno = ?);";

	public void deleteByRoom(final Connection conn, final String roomNo) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_ROOM);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_MOBILEINFOID = "SELECT trd.trd_mobile_info_id FROM token_key tk LEFT JOIN token_room_detail trd "
			+ "ON tk.tk_token_detail_id = trd.trd_id WHERE tk.tk_token_header_id = ? AND tk.tk_private_key = ?;";

	public int selectMobileInfoId(final Connection conn, final String publicKey, final String privateKey)
			throws SQLException {

		PreparedStatement psmt = null;

		int mobildId = -1;

		try {
			psmt = conn.prepareStatement(SELECT_MOBILEINFOID);

			int i = 0;
			psmt.setString(++i, publicKey);
			psmt.setString(++i, privateKey);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				mobildId = rs.getInt("trd_mobile_info_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return mobildId;
	}

	private final static String DELETE_BY_TOKEN_ID = "DELETE FROM token_key WHERE tk_token_detail_id = ? ";

	public void deleteByDetailId(final Connection conn, final int detailId) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_TOKEN_ID);

			int i = 0;
			psmt.setInt(++i, detailId);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_BY_TOKEN = "DELETE FROM token_key WHERE tk_token_header_id = ?";

	public void deleteByToken(final Connection conn, final String token) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_TOKEN);

			int i = 0;
			psmt.setString(++i, token);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

}
