package com.sidc.dao.sits.movieratinglanguage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;

public class MovieRatingLanguageDao {
	private MovieRatingLanguageDao() {
	}

	private static class LazyHolder {
		public static final MovieRatingLanguageDao INSTANCE = new MovieRatingLanguageDao();
	}

	public static MovieRatingLanguageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_GRADE = "SELECT lang_code, grade_desc FROM movie_rating_language WHERE grade = ? ;";

	public List<LangsBean> selectByGrade(final Connection conn, final int grade) throws SQLException {
		PreparedStatement psmt = null;
		List<LangsBean> list = new ArrayList<LangsBean>();

		try {
			psmt = conn.prepareStatement(SELECT_BY_GRADE);
			
			int i = 0;
			psmt.setInt(++i, grade);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LangsBean(rs.getString("lang_code"), rs.getString("grade_desc")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
