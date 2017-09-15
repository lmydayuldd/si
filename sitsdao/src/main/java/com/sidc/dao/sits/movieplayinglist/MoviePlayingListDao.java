package com.sidc.dao.sits.movieplayinglist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class MoviePlayingListDao {
	private MoviePlayingListDao() {
	}

	private static class LazyHolder {
		public static final MoviePlayingListDao INSTANCE = new MoviePlayingListDao();
	}

	public static MoviePlayingListDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String DELETE_BY_STBIP = "DELETE FROM movie_playing_list WHERE stb_ip = ?;";

	public void deleteByStbIp(final Connection conn, final String stbIp) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_STBIP);
			int i = 0;
			psmt.setString(++i, stbIp);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String INSERT = "INSERT INTO movie_playing_list(id,movie_volume_id,stb_ip,start_date_time,movie_id) VALUES (?,?,?,NOW(),?);";

	public void insert(final Connection conn, final String volumeId, final String stbIp, final String movieId)
			throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(INSERT);
			int i = 0;
			psmt.setString(++i, UUID.randomUUID().toString().replace("-", ""));
			psmt.setString(++i, volumeId);
			psmt.setString(++i, stbIp);
			psmt.setString(++i, movieId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
