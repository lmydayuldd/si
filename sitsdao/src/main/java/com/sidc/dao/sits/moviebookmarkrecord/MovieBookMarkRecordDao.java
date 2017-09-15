package com.sidc.dao.sits.moviebookmarkrecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Allen Huang
 *
 */
public class MovieBookMarkRecordDao {

	private MovieBookMarkRecordDao() {
	}

	private static final class lazyHolder {
		public static MovieBookMarkRecordDao INSTANCE = new MovieBookMarkRecordDao();
	}

	public static MovieBookMarkRecordDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String DELETE_MOVIE_BOOKMARK_RECORD = "DELETE FROM movie_bookmark_record "
			+ "WHERE room_no = ?";

	public void deleteMovieBookMarkRecord(final Connection conn, final String roomno) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_MOVIE_BOOKMARK_RECORD);

			int i = 0;
			psmt.setString(++i, roomno);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_ROOMNO_BY_ROOMNO = "UPDATE movie_bookmark_record SET room_no = ? ,"
			+ "stb_ip = (SELECT ip FROM stb WHERE room_no = ? LIMIT 1) WHERE room_no = ?";

	public void updateRoomNoIP(final Connection conn, final String oldRoomNo, final String newRoomNo)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_ROOMNO_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, newRoomNo);
			psmt.setString(++i, newRoomNo);
			psmt.setString(++i, oldRoomNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_TIMESTAMP = "SELECT timestamp FROM movie_bookmark_record "
			+ "WHERE source_filename = ? AND stb_ip = ? AND room_no = ? ;";

	public int findTimeStamp(final Connection conn, final String stpIp, final String roomNo, final String fileName)
			throws SQLException {
		PreparedStatement psmt = null;

		int timeStamp = 0;
		try {
			psmt = conn.prepareStatement(SELECT_TIMESTAMP);

			int i = 0;
			psmt.setString(++i, fileName);
			psmt.setString(++i, stpIp);
			psmt.setString(++i, roomNo);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				timeStamp = rs.getInt("timestamp");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return timeStamp;
	}

	private final static String IS_EXIST_BY_MOVIE = "SELECT timestamp FROM movie_bookmark_record "
			+ "WHERE source_filename = ? AND stb_ip = ? AND room_no = ? AND NOW() <= expire_time;";

	public boolean isExistByMovie(final Connection conn, final String stpIp, final String roomNo, final String fileName)
			throws SQLException {
		PreparedStatement psmt = null;

		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST_BY_MOVIE);

			int i = 0;
			psmt.setString(++i, fileName);
			psmt.setString(++i, stpIp);
			psmt.setString(++i, roomNo);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isExist = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}

}
