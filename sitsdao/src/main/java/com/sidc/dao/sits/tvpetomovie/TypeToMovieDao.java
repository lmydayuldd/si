package com.sidc.dao.sits.tvpetomovie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeToMovieDao {

	private TypeToMovieDao() {
	}

	private static final class lazyHolder {
		public static TypeToMovieDao INSTANCE = new TypeToMovieDao();
	}

	public static TypeToMovieDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_TYPEID_BY_MOVIEID = "SELECT type_id FROM type_to_movie WHERE movie_id = ?;";

	public String selectTypeID(final Connection conn, final String movieId) throws SQLException {
		PreparedStatement psmt = null;

		String typeId = null;

		try {
			psmt = conn.prepareStatement(SELECT_TYPEID_BY_MOVIEID);

			int i = 0;
			psmt.setString(++i, movieId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				typeId = rs.getString("type_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return typeId;
	}

}
