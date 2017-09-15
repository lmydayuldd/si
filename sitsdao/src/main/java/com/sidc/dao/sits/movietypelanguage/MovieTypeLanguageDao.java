package com.sidc.dao.sits.movietypelanguage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;
import com.sidc.blackcore.api.sits.movie.bean.MovieCategoryBean;

public class MovieTypeLanguageDao {
	private MovieTypeLanguageDao() {
	}

	private static class LazyHolder {
		public static final MovieTypeLanguageDao INSTANCE = new MovieTypeLanguageDao();
	}

	public static MovieTypeLanguageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_TYPEID = "SELECT lang_code,type_desc FROM movie_type_language WHERE type_id = ?";

	public List<LangsBean> selectByTypeid(final Connection conn, final String typeId) throws SQLException {
		PreparedStatement psmt = null;
		List<LangsBean> list = new ArrayList<LangsBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_TYPEID);

			int i = 0;
			psmt.setString(++i, typeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LangsBean(rs.getString("lang_code"), rs.getString("type_desc")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
