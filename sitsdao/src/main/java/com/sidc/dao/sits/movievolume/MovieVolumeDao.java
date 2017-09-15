package com.sidc.dao.sits.movievolume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sidc.blackcore.api.sits.movie.bean.VideoServerInfoBean;

public class MovieVolumeDao {
	private MovieVolumeDao() {
	}

	private static class LazyHolder {
		public static final MovieVolumeDao INSTANCE = new MovieVolumeDao();
	}

	public static MovieVolumeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_MOVIE_TOTAL = "SELECT volume_id ,(SELECT COUNT(*) FROM movie_playing_list mpl WHERE mpl.movie_volume_id = "
			+ "mv.volume_id) AS total FROM movie_volume mv ORDER BY total ASC LIMIT 1;";

	public String selectVolumeIdWithBalance(final Connection conn) throws SQLException {
		PreparedStatement psmt = null;
		String id = null;
		try {
			psmt = conn.prepareStatement(SELECT_MOVIE_TOTAL);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getString("volume_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_INFO_BY_ID = "SELECT scheme,host,port,volume FROM movie_volume WHERE volume_id = ?";

	public VideoServerInfoBean selectInfo(final Connection conn, final String volumeId) throws SQLException {
		PreparedStatement psmt = null;

		VideoServerInfoBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_INFO_BY_ID);

			int i = 0;
			psmt.setString(++i, volumeId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new VideoServerInfoBean(rs.getString("scheme"), rs.getString("host"), rs.getString("port"),
						rs.getString("volume"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String SELECT_INFO = "SELECT scheme,host,port,volume FROM movie_volume LIMIT 1;";

	public VideoServerInfoBean selectInfo(final Connection conn) throws SQLException {
		PreparedStatement psmt = null;

		VideoServerInfoBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_INFO);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new VideoServerInfoBean(rs.getString("scheme"), rs.getString("host"), rs.getString("port"),
						rs.getString("volume"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}
}
