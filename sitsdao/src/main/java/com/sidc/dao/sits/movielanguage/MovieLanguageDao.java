package com.sidc.dao.sits.movielanguage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.movie.bean.MovieInfoBean;
import com.sidc.blackcore.api.sits.movie.bean.MovieListBean;

public class MovieLanguageDao {
	private MovieLanguageDao() {
	}

	private static class LazyHolder {
		public static final MovieLanguageDao INSTANCE = new MovieLanguageDao();
	}

	public static MovieLanguageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_MOVIEID = "SELECT lang_code, movie_name,director,actor,introduction,source_filename FROM movie_language WHERE movie_id = ? ";

	public List<MovieInfoBean> selectByMovieid(final Connection conn, final String movieId) throws SQLException {
		PreparedStatement psmt = null;
		List<MovieInfoBean> list = new ArrayList<MovieInfoBean>();

		try {
			psmt = conn.prepareStatement(SELECT_BY_MOVIEID);
			int i = 0;
			psmt.setString(++i, movieId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new MovieInfoBean(rs.getString("lang_code"), rs.getString("movie_name"),
						rs.getString("director"), rs.getString("actor"), rs.getString("introduction"),
						rs.getString("source_filename")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT = "SELECT movie_name,director,actor,introduction,source_filename FROM movie_language "
			+ "WHERE movie_id = ? AND lang_code = ?";

	public MovieListBean select(final Connection conn, MovieListBean entity, final String langCode)
			throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(SELECT);
			int i = 0;
			psmt.setString(++i, entity.getId());
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				entity.setActor(rs.getString("actor"));
				entity.setDirector(rs.getString("director"));
				entity.setIntroduction(rs.getString("introduction"));
				entity.setName(rs.getString("movie_name"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String SELECT_SOURCE = "SELECT source_filename FROM movie_language WHERE movie_id = ? AND lang_code = ?";

	public String selectSourceByMovieId(final Connection conn, final String movieId, final String langCode)
			throws SQLException {
		PreparedStatement psmt = null;
		String srouceName = null;
		try {
			psmt = conn.prepareStatement(SELECT_SOURCE);
			int i = 0;
			psmt.setString(++i, movieId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				srouceName = rs.getString("source_filename");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return srouceName;
	}

	private final static String SELECT_MOVIE_NAME = "SELECT movie_name FROM movie_language "
			+ "WHERE movie_id = ? AND lang_code = ?";

	public String findMovieName(final Connection conn, final String movieId, final String langCode)
			throws SQLException {
		PreparedStatement psmt = null;

		String movieName = null;

		try {
			psmt = conn.prepareStatement(SELECT_MOVIE_NAME);
			int i = 0;
			psmt.setString(++i, movieId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				movieName = rs.getString("movie_name");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return movieName;
	}
}
