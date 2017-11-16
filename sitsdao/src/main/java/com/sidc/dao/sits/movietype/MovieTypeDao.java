package com.sidc.dao.sits.movietype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.movie.bean.MovieCategoryBean;
import com.sidc.blackcore.api.sits.movie.bean.MoviesCatalogueBean;

public class MovieTypeDao {
	private MovieTypeDao() {
	}

	private static class LazyHolder {
		public static final MovieTypeDao INSTANCE = new MovieTypeDao();
	}

	public static MovieTypeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT = "SELECT type_id,seq,protected_flag FROM movie_type WHERE suspend = 'N' ORDER BY seq ASC ";

	public List<MovieCategoryBean> select(final Connection conn) throws SQLException {
		PreparedStatement psmt = null;
		List<MovieCategoryBean> list = new ArrayList<MovieCategoryBean>();
		try {
			psmt = conn.prepareStatement(SELECT);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new MovieCategoryBean(rs.getString("type_id"), rs.getInt("seq"),
						rs.getString("protected_flag").equals("Y") ? true : false));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_WITH_LANGCODE = "SELECT mt.type_id,mt.seq,mt.protected_flag,mtl.type_desc,mtl.icon_url "
			+ "FROM movie_type mt LEFT JOIN movie_type_language mtl ON mt.type_id = mtl.type_id AND  mtl.lang_code = ? "
			+ "WHERE mt.suspend = 'N' ORDER BY mt.seq ASC";

	public List<MoviesCatalogueBean> select(final Connection conn, final String langCode) throws SQLException {
		PreparedStatement psmt = null;
		List<MoviesCatalogueBean> list = new ArrayList<MoviesCatalogueBean>();
		try {
			psmt = conn.prepareStatement(SELECT_WITH_LANGCODE);

			int i = 0;
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new MoviesCatalogueBean(rs.getString("type_id"), rs.getString("type_desc"), rs.getInt("seq"),
						rs.getString("protected_flag").equals("Y") ? true : false, rs.getString("icon_url")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
