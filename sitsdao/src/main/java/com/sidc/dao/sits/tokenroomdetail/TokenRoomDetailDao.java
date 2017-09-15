package com.sidc.dao.sits.tokenroomdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TokenRoomDetailDao {

	private static final class lazyHolder {
		public static TokenRoomDetailDao INSTANCE = new TokenRoomDetailDao();
	}

	public static TokenRoomDetailDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String DELETE = "DELETE FROM token_room_detail WHERE trd_token_header_id = ? AND trd_roomno = ? "
			+ "AND trd_stbip IS NULL;";

	public void delete(final Connection conn, final String roomNo, final String tokenId) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setString(++i, tokenId);
			psmt.setString(++i, roomNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_TOKEN = "DELETE FROM token_room_detail WHERE trd_roomno = ? ;";

	public void deleteByRoom(final Connection conn, final String roomNo) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_TOKEN);

			int i = 0;
			psmt.setString(++i, roomNo);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String CHECK_MOBILE_INFO = "SELECT trd_id FROM token_room_detail "
			+ "WHERE trd_token_header_id = ? AND trd_roomno = ? AND trd_stbip = ? AND trd_mobile_info_id = ? ";

	public boolean checkMobileInfo(final Connection conn, final String roomNo, final String stbIp, final String tokenId,
			final int mobileInfoId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;

		try {
			psmt = conn.prepareStatement(CHECK_MOBILE_INFO);

			int i = 0;
			psmt.setString(++i, tokenId);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, stbIp);
			psmt.setInt(++i, mobileInfoId);

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

	private final static String INSERT_BY_ROOMNO = "INSERT INTO token_room_detail(trd_roomno,trd_token_header_id,trd_creation_time) "
			+ "VALUES (?,?,now()) ;";

	public void insertByRoomNo(final Connection conn, final String roomNo, final String tokenId) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(INSERT_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setString(++i, tokenId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String INSERT = "INSERT INTO token_room_detail(trd_token_header_id,trd_roomno,trd_stbip,"
			+ "trd_mobile_info_id,trd_creation_time)" + "VALUES(?,?,?,?,NOW())";

	public int insert(final Connection conn, final String roomNo, final String stbIp, final String tokenId,
			final int mobileInfoId) throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, tokenId);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, stbIp);
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

	private final static String SELECT_TOKENID = "SELECT trd_token_header_id FROM token_room_detail WHERE trd_roomno = ? "
			+ "GROUP BY trd_token_header_id;";

	public List<String> selectTokenId(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;

		List<String> list = new ArrayList<String>();

		try {
			psmt = conn.prepareStatement(SELECT_TOKENID);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("trd_token_header_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SELECT_MOBILEID = "SELECT trd_mobile_info_id FROM token_room_detail "
			+ "WHERE trd_token_header_id = ? AND trd_roomno = ? ";

	public List<String> selectMobileInfoId(final Connection conn, final String roomNo, final String tokenId)
			throws SQLException {

		PreparedStatement psmt = null;
		List<String> list = new ArrayList<String>();

		try {
			psmt = conn.prepareStatement(SELECT_MOBILEID);

			int i = 0;
			psmt.setString(++i, tokenId);
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("trd_mobile_info_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE_TOKEN_BY_HEADERID = "DELETE FROM token_room_detail WHERE trd_token_header_id = ? ;";

	public void deleteByTokenHeaderId(final Connection conn, final String tokenHeaderId) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_TOKEN_BY_HEADERID);

			int i = 0;
			psmt.setString(++i, tokenHeaderId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_ROOMNO = "SELECT trd_roomno FROM token_room_detail WHERE trd_mobile_info_id IN (SELECT mi_id "
			+ "FROM mobile_info WHERE mi_device = ?);";

	public String selectRoomNo(final Connection conn, final String device) throws SQLException {

		PreparedStatement psmt = null;
		String roomNo = null;

		try {
			psmt = conn.prepareStatement(SELECT_ROOMNO);

			int i = 0;
			psmt.setString(++i, device);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				roomNo = rs.getString("trd_roomno");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return roomNo;
	}

	private final static String SELECT_ID = "SELECT trd_id FROM token_room_detail WHERE trd_mobile_info_id = ? AND trd_roomno = ? ";

	public List<Integer> selectId(final Connection conn, final String roomNo, final int mobileId) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			psmt = conn.prepareStatement(SELECT_ID);

			int i = 0;
			psmt.setInt(++i, mobileId);
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("trd_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE_BY_MOBILEID = "DELETE FROM token_room_detail WHERE trd_roomno = ? AND trd_mobile_info_id = ?;";

	public void deleteByRoomMobileId(final Connection conn, final String roomNo, final int mobileId)
			throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_MOBILEID);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setInt(++i, mobileId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

}
