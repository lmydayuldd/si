package com.sidc.dao.sits.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.movie.bean.MovieBean;
import com.sidc.blackcore.api.sits.movie.bean.MovieListBean;

public class MovieDao {
	private MovieDao() {
	}

	private static class LazyHolder {
		public static final MovieDao INSTANCE = new MovieDao();
	}

	public static MovieDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_TYPEID = "SELECT m.movie_id,m.source_filename,m.price,m.playtime,m.grade,m.post_location,m.seq,"
			+ "m.active_date,m.is_enable_multi_lang_source FROM type_to_movie ttm LEFT JOIN movie m ON m.movie_id = ttm.movie_id"
			+ " WHERE m.suspend = 'N' AND CURDATE() <= STR_TO_DATE(REPLACE(m.expired_date, '-', ''), '%Y%m%d') AND ttm.type_id = ? ORDER BY m.seq ASC ;";

	public List<MovieBean> selectByTypeid(final Connection conn, final String typeId) throws SQLException {
		PreparedStatement psmt = null;
		List<MovieBean> list = new ArrayList<MovieBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_TYPEID);

			int i = 0;
			psmt.setString(++i, typeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new MovieBean(rs.getString("movie_id"), rs.getString("source_filename"), rs.getFloat("price"),
						rs.getInt("playtime"), rs.getInt("grade"), rs.getString("post_location"), rs.getInt("seq"),
						rs.getString("active_date"), rs.getBoolean("is_enable_multi_lang_source")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT = "SELECT m.movie_id,m.source_filename,m.price,m.playtime,m.grade,m.post_location,m.seq,m.active_date,"
			+ "(SELECT mrl.grade_desc FROM movie_rating_language mrl WHERE mrl.grade = m.grade AND mrl.lang_code = ?) AS grade_name "
			+ "FROM type_to_movie ttm LEFT JOIN movie m ON m.movie_id = ttm.movie_id "
			+ "WHERE m.suspend = 'N' AND CURDATE() <= STR_TO_DATE(REPLACE(m.expired_date, '-', ''), '%Y%m%d') "
			+ "AND ttm.type_id = ? ORDER BY m.seq ASC ;";

	public List<MovieListBean> select(final Connection conn, final String typeId, final String langCode)
			throws SQLException {
		PreparedStatement psmt = null;
		List<MovieListBean> list = new ArrayList<MovieListBean>();
		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setString(++i, typeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new MovieListBean(rs.getString("movie_id"), rs.getString("source_filename"),
						rs.getString("price"), rs.getString("playtime"), rs.getString("post_location")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT source_filename FROM movie WHERE movie_id = ?;";

	public String selectSourceNameById(final Connection conn, final String movieId) throws SQLException {
		PreparedStatement psmt = null;
		String sourceName = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setString(++i, movieId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				sourceName = rs.getString("source_filename");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return sourceName;
	}
}
