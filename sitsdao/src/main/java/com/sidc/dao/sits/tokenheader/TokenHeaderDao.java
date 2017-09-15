package com.sidc.dao.sits.tokenheader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sidc.blackcore.api.sits.token.bean.TokenInfoBean;

public class TokenHeaderDao {

	private static final class lazyHolder {
		public static TokenHeaderDao INSTANCE = new TokenHeaderDao();
	}

	public static TokenHeaderDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO token_header(th_id,th_type,th_expired_time,th_creation_time) VALUES (?,?,?,now());";

	public String insert(final Connection conn, final int type, final Timestamp expiredTime) throws SQLException {
		PreparedStatement psmt = null;

		final String uuid = UUID.randomUUID().toString();

		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, uuid);
			psmt.setInt(++i, type);
			psmt.setTimestamp(++i, expiredTime);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return uuid;
	}

	private final static String CHECK_EXPIRED_BY_ROOMNO = "SELECT COUNT(*) AS count FROM token_header TH "
			+ "LEFT JOIN token_room_detail TRD ON TRD.trd_token_header_id = TH.th_id "
			+ "WHERE TH.th_type = ? AND TRD.trd_roomno = ? AND TH.th_id = ? " + "AND NOW() < TH.th_expired_time ;";

	public boolean checkExpired(final Connection conn, final String token, final int type, final String roomNo)
			throws SQLException {
		PreparedStatement psmt = null;

		boolean isPass = false;

		try {
			psmt = conn.prepareStatement(CHECK_EXPIRED_BY_ROOMNO);

			int i = 0;
			psmt.setInt(++i, type);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, token);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt("count") > 0) {
					isPass = true;
				}
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String CHECK_EXPIRED_BY_ROOM_NO = "SELECT th_id FROM token_header TH "
			+ "LEFT JOIN token_room_detail TRD ON TRD.trd_token_header_id = TH.th_id "
			+ "WHERE TH.th_type = ? AND TRD.trd_roomno = ? AND NOW() < TH.th_expired_time ;";

	public boolean checkExpired(final Connection conn, final int type, final String roomNo) throws SQLException {
		PreparedStatement psmt = null;

		boolean isPass = false;

		try {
			psmt = conn.prepareStatement(CHECK_EXPIRED_BY_ROOM_NO);

			int i = 0;
			psmt.setInt(++i, type);
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (!rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String CHECK_EXPIRED_WITH_KEY = "SELECT TH.th_id FROM token_header TH "
			+ "LEFT JOIN token_room_detail TRD ON TRD.trd_token_header_id = TH.th_id "
			+ "LEFT JOIN token_key TK ON TRD.trd_id = TK.tk_token_detail_id "
			+ "WHERE TH.th_type = ? AND TRD.trd_roomno = ? AND TH.th_id = ? AND TK.tk_private_key = ? "
			+ "AND NOW() < TH.th_expired_time ;";

	public boolean checkExpiredWithKey(final Connection conn, final String token, final String privateKey,
			final int type, final String roomNo) throws SQLException {
		PreparedStatement psmt = null;

		boolean isPass = false;

		try {
			psmt = conn.prepareStatement(CHECK_EXPIRED_WITH_KEY);

			int i = 0;
			psmt.setInt(++i, type);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, token);
			psmt.setString(++i, privateKey);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT = "SELECT TH.th_id FROM TOKEN_HEADER TH LEFT JOIN token_room_detail TRD "
			+ "ON TH.th_id = TRD.trd_token_header_id WHERE TRD.trd_roomno = ? AND NOW() < TH.th_expired_time LIMIT 1; ";

	public String select(final Connection conn, final String roomNo) throws SQLException {
		PreparedStatement psmt = null;

		String token = null;

		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, roomNo);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				token = rs.getString("th_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return token;
	}

	private final static String CHECK_EXPIRED_WITH_CHAT = "SELECT th_id FROM token_header th "
			+ "LEFT JOIN token_staff_detail tsd ON tsd.tsd_token_header_id = th.th_id "
			+ "WHERE th.th_type = ? AND tsd.tsd_staff_id = ? AND NOW() < th.th_expired_time ;";

	public boolean checkExpiredWithChat(final Connection conn, final int type, final String staffId)
			throws SQLException {
		PreparedStatement psmt = null;

		boolean isPass = false;

		try {
			psmt = conn.prepareStatement(CHECK_EXPIRED_WITH_CHAT);

			int i = 0;
			psmt.setInt(++i, type);
			psmt.setString(++i, staffId);

			ResultSet rs = psmt.executeQuery();

			if (!rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String DELETE = "DELETE FROM token_header WHERE NOW() >= th_expired_time ;";

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

	private final static String DELETE_ID = "DELETE FROM token_header WHERE th_id = ? ;";

	public void deleteById(final Connection conn, final String id) throws SQLException {
		PreparedStatement psmt = null;

		try {

			psmt = conn.prepareStatement(DELETE_ID);

			int i = 0;
			psmt.setString(++i, id);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_BY_TYPE_FAILURE = "DELETE FROM token_header WHERE th_type = ? AND NOW() >= th_expired_time ;";

	public void deleteByTypeFailure(final Connection conn, final int type) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_TYPE_FAILURE);
			int i = 0;
			psmt.setInt(++i, type);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String CHECK_TOKEN_EXPIRED_WITH_CHAT = "SELECT th_id FROM token_header th WHERE th.th_id = ? AND th.th_type = 1 "
			+ "AND NOW() < th.th_expired_time;";

	public boolean checkTokenExpiredWithChat(final Connection conn, final String id) throws SQLException {
		PreparedStatement psmt = null;

		boolean isPass = false;

		try {
			psmt = conn.prepareStatement(CHECK_TOKEN_EXPIRED_WITH_CHAT);

			int i = 0;
			psmt.setString(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT_WITH_EXPIRED = "SELECT th_id,th_type FROM token_header WHERE NOW() >= th_expired_time ;";

	public List<TokenInfoBean> selectWithExpired(final Connection conn) throws SQLException {
		PreparedStatement psmt = null;

		List<TokenInfoBean> list = new ArrayList<TokenInfoBean>();

		try {
			psmt = conn.prepareStatement(SELECT_WITH_EXPIRED);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new TokenInfoBean(rs.getString("th_id"), rs.getInt("th_type")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
